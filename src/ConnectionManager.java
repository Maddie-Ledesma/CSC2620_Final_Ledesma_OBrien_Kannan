import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private Connection connection;
    boolean isValid;

    /**
     * Default constructor
     */
    public ConnectionManager(String databaseName, String user, String password) {
        this.isValid = true;
        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
        }
        catch(SQLException e){
            this.isValid = false;
        }
    }

    public boolean isValid(){
        return this.isValid;
    }

    public Connection getConnection() {
        return connection;
    }
}
