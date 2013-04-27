package dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import metier.Categorie;
import metier.Entite;





/**
 * Cette classe effectue les accès a la DataBase (DB) pour la classe Categorie.
 *
 * @see Categorie
 */
public final class CategorieDAO extends AbstractDAO {
	 
	// ======================================
    // =             Attributs             =
    // ======================================
    private static final String TABLE = "T_CATEGORIE";
    private static final String COLUMNS = "ID_CATEGORIE, NOM, DESCRIPTION";
    
    
    // Utilisé pour avoir un id unique avec UniqueIdGenerator
    private static final String COUNTER_NAME = "Categorie";
    
    

    // ======================================================
    // =           methodes Business  (logique métier)      =
    // ======================================================
    protected String getInsertSqlStatement(final Entite entite) {
        final Categorie categorie = (Categorie) entite;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + categorie.getId() + "', '" + categorie.getNom() + "', '" + categorie.getDescription() + "' )";
        return sql;
    }

    protected String getDeleteSqlStatement(final String id) {
        final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
        return sql;
    }

    protected String getUpdateSqlStatement(final Entite entite) {
        final Categorie category = (Categorie) entite;
        final String sql;
        sql = "UPDATE " + TABLE + " SET NOM = '" + category.getNom() + "', DESCRIPTION = '" + category.getDescription() + "' WHERE ID_CATEGORIE = '" + category.getId() + "' ";
        return sql;
    }

    protected String getSelectSqlStatement(final String id) {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE ID_CATEGORIE = '" + id + "' ";
        return sql;
    }

    protected String getSelectAllSqlStatement() {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE;
        return sql;
    }

    protected Entite transformResultset2Entite(final ResultSet resultSet) throws SQLException {
        final Categorie categorie;
        categorie = new Categorie(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        return categorie;
    }

	protected String getCounterName() {
		return COUNTER_NAME;
	}
}

