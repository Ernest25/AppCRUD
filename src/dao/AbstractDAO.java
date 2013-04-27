package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


import metier.Entite;
import exception.DataAccessException;
import exception.DuplicateKeyException;
import exception.ObjectNotFoundException;


/**
 * Cette classe implemente le "Data Acces Object (DAO) Design Pattern".
 * Elle utilise JDBC pour stocker les objets dans la DB DataBase.
 * Chaque classe concrete DAO se doit de heriter de cette classe.
 */

public abstract class AbstractDAO implements DAOConstants {

    // ======================================
    // =             Attributs              =
    // ======================================
	
	
	
  
    // ======================================
    // =            Block Static            =
    // ======================================
    static {
        // Charge le driver JDBC 
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
             e.printStackTrace();
        }
        
    }
    
    

    // ======================================
    // =          methodes Business         =
    // ======================================
    
    /**
     * Cette methode permet d'obtenir les attributs d'un objet entit� de la BD (Base de Donn�es)
     * 
     * @param id
     * @return
     * @throws ObjectNotFoundException
     */
    public final Entite findByPrimaryKey(final String id) throws ObjectNotFoundException 
    {
    	return this.select(id);
    }
    
    
    /**
     * Cette methode permet d'obtenir tous les attributs d'un objet entit� a partir de la BD.
     *
     * @param id identifiant de l'objet qui doit �tre trouv� dans la couche persistante 
     * @return Entite l'objet tous ses attributs 
     * @throws ObjectNotFoundException est d�lench�  si l'objet id n'est pas trouv� dans la couche persistante 
     * @throws DataAccessException     est d�lench� s'il y a un probl�me de persistance
     */
    public final Entite select(final String id) throws ObjectNotFoundException 
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Entite entite;

        try {
            // retourne la connexion � la BD  
            connection = getConnection();
            statement = connection.prepareStatement(getSelectSqlStatement(id));

            // Selectionne une ligne, enregistrement (Row)
            resultSet = statement.executeQuery(getSelectSqlStatement(id));
            if (!resultSet.next())
                throw new ObjectNotFoundException();

            // remplit (populate) l'objet courrant 
            entite = transformResultset2Entite(resultSet);

        } catch (SQLException e) {
            // Une sev�re SQL Exception est attrap�e
            displaySqlException(e);
            throw new DataAccessException("Cannot get data from the database", e);
        } finally {
            // Close
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }

        return entite;
    }
    
    

    /**
     * Cette methode retourne tous les objets � partir de la BD
     *
     * @return collection de Entite
     * @throws ObjectNotFoundException est d�clench�e si la collection est vide
     * @throws DataAccessException     est d�clench�e si il y a un probl�me de persistance
     */
    public final Collection findAll() throws ObjectNotFoundException 
    {
    	return selectAll();
    }
    
    
    /**
     * Cette methode retourne tous les objets � partir de la DataBase.
     *
     * @return collection d'Entites
     * @throws ObjectNotFoundException est d�clench�e si la collection est vide
     * @throws DataAccessException     est d�clench�e si il y a un probl�me de persistance 
     */
    public final Collection selectAll() throws ObjectNotFoundException 
    {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final Collection entites = new ArrayList();
        	
        try {
            // retourne la connexion � la DataBase 
            connection = getConnection();
            statement = connection.prepareStatement(getSelectAllSqlStatement());

            // Selectionne une ligne (Row)
            resultSet = statement.executeQuery(getSelectAllSqlStatement());

            while (resultSet.next()) {
                // renseigne les donn�es de la collection
                entites.add(transformResultset2Entite(resultSet));
            }

            if (entites.isEmpty())
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            // Une S�v�re SQL Exception est attrap�e
            displaySqlException(e);
            throw new DataAccessException("Cannot get data from the database", e);
        } finally {
            // Close
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }

        return entites;
    }
    
    

    /**
     * Cette methode Insert  un objet dans la database.
     *
     * @param entite objet m�tier a inserer dans la DB
     * @throws DuplicateKeyException est d�clench� quand un identique objet est d�j� dans la couche persistance
     * @throws DataAccessException   est d�clench� si il y a un probl�me de persistance
     */
    public final void insert(final Entite entite) throws DuplicateKeyException
    {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // retourne la connexion a la Data Base
            connection = getConnection();
            statement = connection.prepareStatement(getInsertSqlStatement(entite));

            // positionne ID de l'objet si n�cessaire
            if ( entite.getId() == null )
            	entite.setId("" + getUniqueId());

            // Insert une ligne (Row)
            statement.executeUpdate(getInsertSqlStatement(entite));

        } catch (SQLException e) {
            // Les data existent d�j� dans la database
            if (e.getErrorCode() == DATA_ALREADY_EXIST) {
                throw new DuplicateKeyException();
            } else {
                // Une S�v�re SQL Exception est atrapp�e
                displaySqlException(e);
                throw new DataAccessException("Cannot insert data into the database", e);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            // Close
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
    }

    /**
     * Cette methode met a jour (Update)  un objet dans la database.
     *
     * @param entite Objet a mettre a jourt dans la database
     * @throws ObjectNotFoundException est d�clench�e si l'objet id n'est pas trouv� dans la database
     * @throws DataAccessException     est d�clench�e si il y a un probl�me de persistance
     */
    public final void update(final Entite entite) throws ObjectNotFoundException 
    {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // retourne une connexion a la database
            connection = getConnection();
            statement = connection.prepareStatement(getUpdateSqlStatement(entite));

            // Met a jour (Update) une ligne (Row)
            if (statement.executeUpdate(getUpdateSqlStatement(entite)) == 0)
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            // Une S�v�re SQL Exception est d�clench�e
            displaySqlException(e);
            throw new DataAccessException("Cannot update data into the database", e);
        } finally {
            // Close
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
    }

    /**
     * Cette methode supprime (Delete) un objet dans la database.
     *
     * @param id identifieur d'un objet � supprimer 
     * @throws ObjectNotFoundException est d�clench�e si l'objet n'est pas trouv�
     * @throws DataAccessException     est d�clench�e si il y a un probl�me de persistance
     */
    public final void delete(final String id) throws ObjectNotFoundException 
    {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // retourne une connexion � la database 
            connection = getConnection();
            statement = connection.prepareStatement(getDeleteSqlStatement(id));

            // Supprime (Delete) une ligne (Row)
            if (statement.executeUpdate(getDeleteSqlStatement(id)) == 0)
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            // Une S�v�re SQL Exception est attrap�e
            displaySqlException(e);
            throw new DataAccessException("Cannot remove data into the database", e);

        } finally {
            // Close
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
    }

    /**
     * Cette methode retourne un identifiant unique gener� par le syst�me. 
     *
     * @return un identifiant unique
     */
    public final String getUniqueId() 
    {
        return UniqueIdGenerator.getInstance().getUniqueId(getCounterName());
    }

    /**
     * Cette methode retourne un identifiant unique  gener� par le syst�me. 
     *
     * @param domainClassName nom de la classe m�tier (domaine) (c-a-d : Categorie, Formation, Session, Cour,  ...)
     * @return un identifiant unique 
     */
    public final String getUniqueId(final String domainClassName) 
    {
        return UniqueIdGenerator.getInstance().getUniqueId(domainClassName);
    }

    
    /**
     * This method returns a database connection.
     *
     * @return a JDBC connection to the petstore database
     * @throws SQLException if a SQl expcetion if found
     */
    public static final Connection getConnection() throws SQLException 
    {
        final Connection connection;
        connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWD_DB);
        return connection;
    }

    /**
     * Cette methode affiche toutes les informations d'une SQL exception. Son code de erreur, state, 
     * message sql  et finalment la stacktrace de l'Exception
     *
     * @param e SQLException que vous desirez afficher
     */
    public static void displaySqlException(final SQLException e) 
    {
    	System.out.println("Error code  : "+e.getErrorCode());
    	System.out.println("SQL state   : " + e.getSQLState());
    	System.out.println("SQL message : " + e.getMessage());
    	e.printStackTrace();
    }

    /**
     * Cette methode affiche toutes les informations d'une SQL exception et un message personalis�.
     * Affiche le sql code error, state, message sql  et  finalment la stacktrace de l'Exception
     *
     * @param message  message personnalis� � afficher
     * @param e       SQLException que vous voulez afficher
     */
    public static void displaySqlException(final String message, final SQLException e) 
    {
    	System.out.println("Message     : " + message);
    	System.out.println("Error code  : " + e.getErrorCode());
    	System.out.println("SQL state   : " + e.getSQLState());
    	System.out.println("SQL message : " + e.getMessage());
        e.printStackTrace();
    }

    // ========================================================================================
    // =             Methodes qui implementent le  Design Pattern "Template Method"           =
    // ========================================================================================
    
    
    /**
     * Est utilis� pour obtenir un identifiant, id  unique avec la classe UniqueIdGenerator
     * 
     * @return
     */
    protected abstract String getCounterName();
    
    
    
    /**
     * Cette methode retourne un "INSERT sql statement"
     * Cette methode . Est utilis� par
     * la  {@link #insert(Entite) insert} methode. Chaque classe concrete  doit redefinir cette methode.
     *
     * @param entite Entite a inserer dans la database
     * @return un "insert sql statement"
     */
    protected abstract String getInsertSqlStatement(Entite entite);

    /**
     * Cette methode retourne un "Delete sql statement"
     * Cette methode implemente le  Design Pattern "Template Method". Est utilis� par
     * la {@link #delete(String) remove} methode. Caque classe concrete doit redefinir cette methode.
     *
     * @param id de l'objet entit� a supprimer
     * @return un  "Delete sql statement"
     */
    protected abstract String getDeleteSqlStatement(String id);

    /**
     * Cette methode retourne un "update sql statement"
     * Cette methode implemente le Design Pattern "Template Method". Est utilis� par
     * la {@link #update(Entite) update} methode. Chaque classe concrete  doit redefinir cette methode.
     *
     * @param entite Entite � mettre a jour (Update) dans la database
     * @return un "update sql statement"
     */
    protected abstract String getUpdateSqlStatement(Entite entite);

    /**
     * Cette methode retourne un "select sql statement"
     * Cette methode implemente le Design Pattern "Template Method". Est utilis�e par
     * la {@link #select(String) select} methode. Chaque classe concrete  doit redefinir cette methode.
     *
     * @param id de l'objet domaine (m�tier) � selectionner
     * @return un "select sql statement"
     */
    protected abstract String getSelectSqlStatement(String id);

    /**
     * Cette methode retourne un "select * sql statement"
     * Cette methode implemente le Design Pattern "Template Method". Est utilis�e par
     * la {@link #selectAll() selectAll} methode. Chaque classe concrete  doit redefinir cette methode.
     *
     * @return un "select * sql statement"
     */
    protected abstract String getSelectAllSqlStatement();
    
    /**
     * Cette methode prend un "resultset" et le transforme en un "Domain Object" , objet m�tier entit�.
     * Cette methode implemente le  Design Pattern "Template Method". Est utilis�e par
     * la {@link #select(String) select} et {@link #selectAll() selectAll} methode. Chaque classe concrete  doit redefinir cette methode.
     *
     * @param resultSet  resultset JDBC, contenant tous les informations pour un enregistrement (ligne) (row)
     * @return un Entite avec ses valeurs
     * @throws SQLException
     */
    protected abstract Entite transformResultset2Entite(ResultSet resultSet) throws SQLException;
	
    
}
