package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import metier.Document;
import metier.Entite;
import metier.Session;
import metier.Utilisateur;
import exception.DataAccessException;
import exception.ObjectNotFoundException;


/**
 * Cette classe fournit l'accès à la database pour la classe Document.
 *
 * @see Document
 */
public final class DocumentDAO extends AbstractDAO {

	// ======================================
    // =             Attributs             =
    // ======================================
    private static final String TABLE = "T_DOCUMENT";
    private static final String COLUMNS = "ID_DOCUMENT, " +
    		"TITRE, FORMAT, DATE_CREATION, DATE_MODIFICATION, URL, DESCRIPTION, NBR_TELECHARGEMENTS, SESSION_FK, AUTEUR_FK";
    
    
    // Utilisé pour avoir un id unique avec UniqueIdGenerator
    private static final String COUNTER_NAME = "Document";


    // ======================================================
    // =           methodes Business  (logique métier)      =
    // ======================================================
    /**
     * Cette methode retourne tous documents de la database pour un SessionId donné.
     *
     * @param SessionId
     * @return collection de documents
     * @throws ObjectNotFoundException est déclenchée si la collection est vide
     * @throws DataAccessException     est déclenchée si il y a un problème de persistance 
     */
    public Collection findAll(final String SessionId) throws ObjectNotFoundException {
    	return selectAll(SessionId);
    }
    /**
     * Cette methode retourne toutes les Documents de la database pour un id d'une session donnée.
     *
     * @param orderId
     * @return collection of OrderLine
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public Collection selectAll(final String SessionId) throws ObjectNotFoundException 
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        final Collection documents = new ArrayList();
        Document  document;

        try {
            // obtenir une connexion à la database 
            connection = getConnection();
            statement = connection.createStatement();

            // Selectione un Row
            final String sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE SESSION_FK = '" + SessionId + "'";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) 
            {
                document = new Document(resultSet.getString(1), 
                		                resultSet.getString(2), 
                		                resultSet.getString(3), 
                		                resultSet.getString(4), 
                		                resultSet.getString(5), 
                		                resultSet.getString(6), 
                		                resultSet.getString(7), 
                		                resultSet.getInt(8),
                		                new Session(resultSet.getString(9)), 
                		                new Utilisateur(resultSet.getString(10)));
                
                // met les données dans la collection
                documents.add(document);
            }

            if (documents.isEmpty())
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            //  SQL Exception est attrapée
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
        return documents;
    }
    
    
    
    ///----------------------------------------------------------------------
    

    protected String getInsertSqlStatement(final Entite entite) {
        final Document document = (Document) entite;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + document.getId() + "', '" + document.getTitre() + "', '" + document.getFormat() + "', '" + document.getDateCreation() + "', '" + document.getDateModifcation()+ "' , '" + document.getUrl()+ "' )";
        return sql;
    }

    protected String getDeleteSqlStatement(final String id) {
        final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
        return sql;
    }

    protected String getUpdateSqlStatement(final Entite entite) {
        final Document document = (Document) entite;
        final String sql;
        sql = "UPDATE " + TABLE + " SET TITRE = '" + document.getTitre() + "', FORMAT = '" + document.getFormat() + "', DATE_CREATION = '" + document.getDateCreation() + "' , DATE_MODIFICATION = '" + document.getDateModifcation() + "' , URL = '" + document.getUrl() + "' , DESCRIPTION = '" + document.getDescription() + "' , NBR_TELECHARGEMENTS = '" + document.getNbrTelechargements() + "' WHERE ID = '" + document.getId() + "' ";
        return sql;
    }

    protected String getSelectSqlStatement(final String id) {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE ID = '" + id + "' ";
        return sql;
    }

    protected String getSelectAllSqlStatement() {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE;
        return sql;
    }

    protected Entite transformResultset2Entite(final ResultSet resultSet) throws SQLException {
        final Document entite;
        entite = new Document(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),resultSet.getString(7), resultSet.getInt(8), new Session(resultSet.getString(9)), new Utilisateur(resultSet.getString(10)));
        return entite;
    }

	protected String getCounterName() {
		return COUNTER_NAME;
	}
}

