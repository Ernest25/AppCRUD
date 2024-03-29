package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import exception.DataAccessException;
import exception.DuplicateKeyException;
import exception.ObjectNotFoundException;




/**
 * This class does all the database access for the class UniqueIdGenerator.
 *
 * @see UniqueIdGenerator
 */
public final class UniqueIdGeneratorDAO {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final String TABLE = "T_COUNTER";

    /**
     * This method is used when a unique id doesn't exist. This method inserts
     * the value '1' into the database. Meaning, 1 is the first identifier.
     *
     * @param name name of the counter
     * @throws DuplicateKeyException is thrown when an identical object is already in the persistent layer
     * @throws DataAccessException   is thrown if there's a persistent problem
     */
    public void insert(final String name) throws DuplicateKeyException {
        Connection connection = null;
        Statement statement = null;
        final String sql;

        try {
            connection = AbstractDAO.getConnection();
            statement = connection.createStatement();

            // Insert a Row
            sql = "INSERT INTO " + TABLE + " VALUES ('" + name + "', '1' )";

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            if (e.getErrorCode() == DAOConstants.DATA_ALREADY_EXIST) {
                throw new DuplicateKeyException();
            } else {
                AbstractDAO.displaySqlException(e);
                throw new DataAccessException("Cannot insert data into the database", e);
            }
        } finally {
            // Close
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                AbstractDAO.displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
    }

    /**
     * This method deletes the counter name from the database.
     *
     * @param name of the counter to be deleted
     * @throws ObjectNotFoundException is thrown if the object id not found in the persistent layer
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public void remove(final String name) throws ObjectNotFoundException {
        Connection connection = null;
        Statement statement = null;
        final String sql;

        try {
            connection = AbstractDAO.getConnection();
            statement = connection.createStatement();

            // Delete a Row
            sql = "DELETE FROM " + TABLE + " WHERE NAME = '" + name + "'";
            if (statement.executeUpdate(sql) == 0)
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            AbstractDAO.displaySqlException(e);
            throw new DataAccessException("Cannot remove data into the database", e);
        } finally {
            // Close
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                AbstractDAO.displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
    }

    /**
     * This method updates the value of the counter in the database.
     *
     * @param name  of the counter to be updated in the database
     * @param value new value to update
     * @throws ObjectNotFoundException is thrown if the object id not found in the database
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public void update(final String name, final int value) throws ObjectNotFoundException {
        Connection connection = null;
        Statement statement = null;
        final String sql;

        try {
            connection = AbstractDAO.getConnection();
            statement = connection.createStatement();

            // Update a Row
            sql = "UPDATE " + TABLE + " SET VALUE = '" + value + "' WHERE NAME = '" + name + "' ";

            if (statement.executeUpdate(sql) == 0)
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            AbstractDAO.displaySqlException(e);
            throw new DataAccessException("Cannot update data into the database", e);
        } finally {
            // Close
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                AbstractDAO.displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
    }

    /**
     * This method return the value of the counter from the database.
     *
     * @param name of the counter to be found in the persistent layer
     * @return value of the counter
     * @throws ObjectNotFoundException is thrown if the object id not found in the persistent layer
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public int select(final String name) throws ObjectNotFoundException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        final String sql;
        int value;

        try {
            connection = AbstractDAO.getConnection();
            statement = connection.createStatement();

            // Select a Row
            sql = "SELECT VALUE FROM " + TABLE + " WHERE NAME = '" + name + "' ";
            resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                throw new ObjectNotFoundException();

            // Set data to current object
            value = resultSet.getInt(1);

        } catch (SQLException e) {
            AbstractDAO.displaySqlException(e);
            throw new DataAccessException("Cannot get data from the database", e);
        } finally {
            // Close
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                AbstractDAO.displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
        return value;
    }
}
