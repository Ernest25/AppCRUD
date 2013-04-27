package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import metier.Commentaire;
import metier.Cour;
import metier.Entite;



/**
 * Cette classe effectue les  accès a la database pour la classe Cour.
 *
 * @see Cour
 */
public final class CourDAO extends AbstractDAO {

	
	// ======================================
    // =             Attributs             =
    // ======================================
    private static final String TABLE = "T_COUR";
    private static final String COLUMNS = "ID, NOMMATIERE, DESCRIPTION, DATE, HEUREDEBUT, HEUREFIN, ADRESSE, SALLE, SESSIONFORMATION_FK";
    
    
    // Utilisé pour avoir un id unique avec UniqueIdGenerator
    private static final String COUNTER_NAME = "Cour";
    
    

    // ======================================================
    // =           methodes Business  (logique métier)      =
    // ======================================================
    protected String getInsertSqlStatement(final Entite entite) {
        final Cour cour = (Cour) entite;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + cour.getId() + "', '" + cour.getNomMatiere() + "', '" + cour.getDescription()+ "', '" + cour.getDate() + "' , '" + cour.getHeureDebut() + "' , '" + cour.getHeureFin() + "' , '" + cour.getAdresse() + "' , '" + cour.getSalle() + "' )";
        return sql;
    }

    protected String getDeleteSqlStatement(final String id) {
        final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
        return sql;
    }

    protected String getUpdateSqlStatement(final Entite entite) {
        final Commentaire commentaire = (Commentaire) entite;
        final String sql;
        sql = "UPDATE " + TABLE + " SET COMMENTAIRE = '" + commentaire.getCommentaire() + "', DATEINSERTION = '" +  commentaire.getDateInsertion()+ "' , STATUTMODERATION = '" +  commentaire.getStatutModeration()+ "' WHERE ID = '" + commentaire.getId() + "' ";
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
        final Commentaire commentaire;
        commentaire = new Commentaire(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        return commentaire;
    }

	protected String getCounterName() {
		return COUNTER_NAME;
	}
}

