package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import metier.Entite;
import metier.Utilisateur;


/**
 * Cette classe fournit l'accès à la database pour la classe Utilisateur.
 *
 * @see Utilisateur
 */
public final class UtilisateurDAO extends AbstractDAO {

	 // ======================================
    // =             Attributes             =
    // ======================================
    private static final String TABLE = "T_UTILISATEUR";
    private static final String COLUMNS = "ID_UTILISATEUR, PASSWORD, NOM, PRENOM , MAIL, ADRESSE, CODE_POSTAL, VILLE, TELEPHONE_FIXE, TELEPHONE_PORTABLE, DATE_INSCRIPTION, TYPE_UTILISATEUR";


    // Utilisé pour avoir un id unique avec UniqueIdGenerator
    private static final String COUNTER_NAME = "Utilisateur";

    // ======================================
    // =           Business methods         =
    // ======================================
    protected String getInsertSqlStatement(final Entite object) {
        final Utilisateur utilisateur = (Utilisateur) object;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + utilisateur.getId() + "', '" + utilisateur.getLogin() + "','" + utilisateur.getMotDePasse() + "','" + utilisateur.getNom() + "', '" + utilisateur.getPrenom() + "', '" + utilisateur.getMail() + "', '" + utilisateur.getAdresse() + "', '" + utilisateur.getCodePostal() + "', '" + utilisateur.getVille() + "', '" + utilisateur.getTelephoneFixe() + "', '" + utilisateur.getTelephonePortable() + "', '" + utilisateur.getDatePremiereInscription() + "', '" + utilisateur.getTypeUtilisateur() + "' )";
        return sql;
    }

    protected String getDeleteSqlStatement(final String id) {
        final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
        return sql;
    }

    protected String getUpdateSqlStatement(final Entite object) {
        final Utilisateur utilisateur = (Utilisateur) object;
        final String sql;
        sql = "UPDATE " + TABLE + " SET PASSWORD = '" + utilisateur.getMotDePasse() + "', NOM = '" + utilisateur.getNom() + "', PRENOM = '" + utilisateur.getPrenom() + "', MAIL = '" + utilisateur.getMail() + "', ADRESSE = '" + utilisateur.getAdresse() + "', CODE_POSTAL = '" + utilisateur.getCodePostal() + "', VILLE = '" + utilisateur.getVille() + "', TELEPHONE_FIXE = '" + utilisateur.getTelephoneFixe() + "', TELEPHONE_PORTABLE = '" + utilisateur.getTelephonePortable() + "', DATE_INSCRIPTION = '" + utilisateur.getDatePremiereInscription() + "', TYPE_UTILISATEUR = '" + utilisateur.getTypeUtilisateur() + "' WHERE ID = '" + utilisateur.getId() + "' ";
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
        final Utilisateur utilisateur;
        utilisateur = new Utilisateur(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12));
        
        return utilisateur;
    }

	protected String getCounterName() {
		return COUNTER_NAME;
	}
}

