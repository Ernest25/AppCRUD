package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import exception.DataAccessException;
import exception.ObjectNotFoundException;

import metier.Commentaire;
import metier.Entite;
import metier.Session;
import metier.Utilisateur;



/**
 * Cette classe fournit les accès à la database pour la classe Commentaire.
 *
 * @see Commentaire
 */
public final class CommentaireDAO extends AbstractDAO {

	// ======================================
    // =             Attributs             =
    // ======================================
    private static final String TABLE = "T_COMMENTAIRE";
    private static final String COLUMNS = "ID_COMMENTAIRE, COMMENTAIRE, DATE_INSERTION, STATUT_MODERATION, SESSION_FK, UTILISATEUR_FK";
    
    
    // Utilisé pour avoir un id unique avec UniqueIdGenerator
    private static final String COUNTER_NAME = "Commentaire";
    
    

    // ======================================================
    // =           methodes Business  (logique métier)      =
    // ======================================================
    
    
    /**
     * Cette methode retourne toutes les commentaires de la database pour un id donné d'une session.
     *
     * @param sessionId
     * @return collection de commentaires
     * @throws ObjectNotFoundException est déclenchée si la collection est vide
     * @throws DataAccessException     est déclenchée si il y a un problème de persistance 
     */
    public Collection findAll(final String sessionId) throws ObjectNotFoundException 
    {
    	return selectAll(sessionId);
    }
    
    /**
     * Cette methode retourne toutes les commentaires de la database pour un id d'une session donnée.
     *
     * @param sessionId
     * @return collection de commentaires
     * @throws ObjectNotFoundException est déclechée si la collection est vide
     * @throws DataAccessException     est déclechée si  il y a un problème de persistance
     */
    public Collection selectAll(final String sessionId) throws ObjectNotFoundException 
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final Collection sessions = new ArrayList();
        
        try {
            // recupere la connexion
            connection = getConnection();
            
            // Selectionne une ligne,  Row
            final String sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE  SESSION_FK = '" + sessionId + "'";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) 
            {
                // rempli la collection
                sessions.add(transformResultset2Entite(resultSet));
            }

            if (sessions.isEmpty())
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
        return sessions;
    }

    

    
    
    ///--------------------------------------------------------------------------------
    
    
    
    protected String getInsertSqlStatement(final Entite entite) {
        final Commentaire commentaire = (Commentaire) entite;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + commentaire.getId() + "', '" + commentaire.getCommentaire() + "', '" + commentaire.getDateInsertion() + "', '" + commentaire.getStatutModeration() + "' , '" + commentaire.getAuteur().getId() + "'  , '" + commentaire.getSession().getId() + "' )";
        return sql;
    }

    protected String getDeleteSqlStatement(final String id) {
        final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID_COMMENTAIRE = '" + id + "'";
        return sql;
    }

    protected String getUpdateSqlStatement(final Entite entite) {
        final Commentaire commentaire = (Commentaire) entite;
        final String sql;
        sql = "UPDATE " + TABLE + " SET COMMENTAIRE = '" + commentaire.getCommentaire() + "', DATE_INSERTION = '" +  commentaire.getDateInsertion()+ "' , STATUT_MODERATION = '" +  commentaire.getStatutModeration()+ "' WHERE ID_COMMENTAIRE = '" + commentaire.getId() + "' ";
        return sql;
    }

    protected String getSelectSqlStatement(final String id) {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE ID_COMMENTAIRE = '" + id + "' ";
        return sql;
    }

    protected String getSelectAllSqlStatement() {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE;
        return sql;
    }

    protected Entite transformResultset2Entite(final ResultSet resultSet) throws SQLException {
        final Commentaire commentaire;
        commentaire = new Commentaire(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), new Utilisateur(resultSet.getString(5)), new Session(resultSet.getString(6)));
        return commentaire;
    }

	protected String getCounterName() {
		return COUNTER_NAME;
	}
}

