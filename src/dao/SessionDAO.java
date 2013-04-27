package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import metier.Entite;
import metier.Formation;
import metier.Session;
import exception.DataAccessException;
import exception.ObjectNotFoundException;


/**
 * Cette classe fournit l'accès à la database pour la classe Session.
 *
 * @see Session
 */
public final class SessionDAO extends AbstractDAO {

    // ======================================
    // =             Attributs             =
    // ======================================
    private static final String TABLE = "T_SESSION";//table dans la BD relationel
    private static final String COLUMNS = "ID_SESSION, NOM, DESCRIPTION, DATE_DEBUT, DATE_FIN, NBR_PLACE, FORMATION_FK";
    
    // Utilisé pour avoir un id unique avec UniqueIdGenerator
    private static final String COUNTER_NAME = "Session";

    
    
    // ======================================================
    // =           methodes Business  (logique métier)      =
    // ======================================================
    /**
     * Cette methode retourne toutes les  sessions de la database pour un id d'une Formation  donné.
     *
     * @param formationId
     * @return collection de Sessions
     * @throws ObjectNotFoundException est déclenchée si la collection est vide
     * @throws DataAccessException     est déclenchée si il y a un problème de persistance 
     */
    public Collection findAll(final String formationId) throws ObjectNotFoundException 
    {
    	return selectAll(formationId);
    }
    
    /**
     * Cette methode retourne toutes les sessions de la database pour un id d'une Formation  donné.
     *
     * @param formationId
     * @return collection de Sessions
     * @throws ObjectNotFoundException est déclenchée si la collection est vide
     * @throws DataAccessException     est déclenchée si il y a un problème de persistance 
     */
    public Collection selectAll(final String formationId) throws ObjectNotFoundException 
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final Collection sessions = new ArrayList();
        
        try {
            // obetient une connexion a la database 
            connection = getConnection();
            
            // Selectionne un Row
            final String sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE FORMATION_FK = '" + formationId + "'";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) 
            {
                // remplit la collection avec des sessions
                sessions.add(transformResultset2Entite(resultSet));
            }

            if (sessions.isEmpty())
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            // SQL Exception est attrapée
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

    
    
    //----------------------------------------------------------------------------
    
    
    
    protected String getInsertSqlStatement(final Entite entite)
    {
        final Session session = (Session) entite;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + session.getId() + "', '" + session.getNom() + "', '" + session.getDescription() + "','" + session.getDateDebut() + "', '" + session.getDateFin() + "', '" + session.getNbrPlace() + "' , '" + session.getFormation().getId() + "')";
        return sql;
    }

    protected String getDeleteSqlStatement(final String id) {
        final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
        return sql;
    }

    protected String getUpdateSqlStatement(final Entite entite) 
    {
        final Session session = (Session) entite;
        final String sql;
        sql = "UPDATE " + TABLE + " SET NOM = '" + session.getNom() + "', DESCRIPTION = '" + session.getDescription() + "',  DATE_DEBUT = '" + session.getDateDebut() + "', DATE_FIN = '" + session.getDateFin() + "', NBR_PLACE = '" + session.getNbrPlace() + "', FORMATION_FK = '" + session.getFormation().getId() + "'   WHERE ID = '" + session.getId() + "' ";
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
        final Session session;
        // FORMAT : Session(String id, String nom, String desc, String dateDebut, String dateFin, int nbrPlace, Formation formation)
        session = new Session(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), new Formation(resultSet.getString(7))); 
        return session;
    }
    
	protected String getCounterName() 
	{
		return COUNTER_NAME;
	}
}

