import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAL
{
    private Connection connection;

    public DAL(String databaseName, String userName, String password)
    {
        InitializeConnection(databaseName, userName, password);
    }
    
    private void InitializeConnection(String databaseName, String user, String password)
    {
        try
        {
            if(connection == null)
            {
               connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
            }
        } 
        catch (SQLException exception)
        {
            System.out.println("Failed to connect to the database" + exception.getMessage());
        }
    }

    


}