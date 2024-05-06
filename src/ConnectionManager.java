import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private Connection connection;

    /**
     * Default constructor
     */
    public ConnectionManager(String databaseName, String user, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
    }

    public Connection getConnection() {
        return connection;
    }
}
