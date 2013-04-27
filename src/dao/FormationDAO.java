package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import metier.Categorie;
import metier.Entite;
import metier.Formation;
import exception.DataAccessException;
import exception.ObjectNotFoundException;



/**
 * Cette classe fournit les accès à la database pour la classe Formation.
 *
 * @see Formation
 */
public final class FormationDAO extends AbstractDAO {

    // ======================================
    // =             Attributs             =
    // ======================================
    private static final String TABLE = "T_FORMATION";//nom de la table dans la BD relationel
    private static final String COLUMNS = "ID, LIBELLE, PREREQUIS, DESCRIPTION, PRIX, CATEGORIE_FK";
    
    
    // Utilisé pour avoir un id unique avec UniqueIdGenerator
    private static final String COUNTER_NAME = "Formation";
    
    
    

    // ======================================================
    // =           methodes Business  (logique métier)      =
    // ======================================================
    /**
     * Cette methode retourne toutes les formations de la database pour un id donné d'une catégorie.
     *
     * @param cotegorieId
     * @return collection de Formation
     * @throws ObjectNotFoundException est déclenchée si la collection est vide
     * @throws DataAccessException     est déclenchée si il y a un problème de persistance 
     */
    public Collection findAll(final String cotegorieId) throws ObjectNotFoundException 
    {
    	return selectAll(cotegorieId);
    }
    
    /**
     * Cette methode retourne toutes les formations de la database pour un id d'unecategorie donnée.
     *
     * @param cotegorieId
     * @return collection de Formations
     * @throws ObjectNotFoundException est déclechée si la collection est vide
     * @throws DataAccessException     est déclechée si  il y a un problème de persistance
     */
    public Collection selectAll(final String cotegorieId) throws ObjectNotFoundException 
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final Collection formations = new ArrayList();
        
        try {
            // recupere la connexion
            connection = getConnection();
            
            // Selectionne une ligne,  Row
            final String sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE  CATEGORIE_FK = '" + cotegorieId + "'";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) 
            {
                // rempli la collection
                formations.add(transformResultset2Entite(resultSet));
            }

            if (formations.isEmpty())
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            // Une Severe SQL Exception est attrapée
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
        return formations;
    }

    
    
    
    protected String getInsertSqlStatement(final Entite entite)
    {
        final Formation formation = (Formation) entite;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + formation.getId() + "', '" + formation.getLibelle() + "', '" + formation.getPreRequis() + "', '" + formation.getDescription() + "', '" + formation.getPrix() + "' )";
        return sql;
    }

    protected String getDeleteSqlStatement(final String id) {
        final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
        return sql;
    }

    protected String getUpdateSqlStatement(final Entite entite) 
    {
        final Formation formation = (Formation) entite;
        final String sql;
        sql = "UPDATE " + TABLE + " SET LIBELLE = '" + formation.getLibelle() + "', PREREQUIS = '" + formation.getPreRequis() + "'  , DESCRIPTION = '" + formation.getDescription() + "'  , PRIX = '" + formation.getPrix() + "'  , CATEGORIE_FK = '" + formation.getCategorie().getId() + "'  WHERE ID = '" + formation.getId() + "' ";
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

    protected Entite transformResultset2Entite(final ResultSet resultSet) throws SQLException
    {
        final Formation formation;
        // FORMAT :  Formation(String id, String libelle, String preRequis, String description, double prix, Categorie categorie)
        formation = new Formation(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), new Categorie(resultSet.getString(6)));
        return formation;
    }
    
	protected String getCounterName() 
	{
		return COUNTER_NAME;
	}
}

