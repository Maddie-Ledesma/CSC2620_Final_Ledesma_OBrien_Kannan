import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implements common database connection
 *
 * @author Maddie
 */
public abstract class AbstractDAL {

    protected Connection connection;

    public AbstractDAL(String databaseName, String userName, String password) throws SQLException {
        InitializeConnection(databaseName, userName, password);
    }

    private void InitializeConnection(String databaseName, String user, String password) throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
