package dao;

/**
 * Cette interface liste touts les paremetres d'acc�s a la BD
 */
public interface DAOConstants {

	/**
     * code d'erreur : quand les donn�es existent d�j�
     */
    int DATA_ALREADY_EXIST = 1062;
	
	/**
     * JDBC Driver
     */
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    /**
     * URL de la base de donn�es
     */
    String URL_DB = "jdbc:mysql://localhost:3306/catalogueFormation";

    /**
     * Username de la BD
     */
    String USER_DB = "root";

    /**
     * Password de la BD
     */
    String PASSWD_DB = "";
}
