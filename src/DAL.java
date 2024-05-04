import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> getInventory()
    {
        int GameId = 0; 
        String GameName = null; 
        int GamePrice = 0;
        String releaseDate = null; 
        int Genre = 0;
        

        List<String> inventory = new ArrayList<String>();
        try
        {
            PreparedStatement myStatement = connection.prepareStatement("Select * From Game");
            myStatement.setInt(1, GameId);
            myStatement.setString(2, GameName);
            myStatement.setInt(3, GamePrice);
            myStatement.setString(4, releaseDate);
            myStatement.setInt(5, Genre);
            ResultSet myRelation = myStatement.executeQuery();
            while(myRelation.next())
            {
               inventory.add(myRelation.getString(GameId));
               inventory.add(myRelation.getString(GameName));
               inventory.add(myRelation.getString(GamePrice));
               inventory.add(myRelation.getString(releaseDate));
               inventory.add(myRelation.getString(Genre));
            }
            return inventory;
        }
        catch(SQLException ex)
        {
            System.out.println("Failed to get inventory" + ex.getMessage());
            return inventory;
        }
    }

    public ResultSet callAddToGameSales(int newSaleId, String GameName, int Price) throws SQLException
    {
        CallableStatement myStoredProcedureCall = connection.prepareCall("{Call addToGameSales(?)}");
        myStoredProcedureCall.setInt(1, newSaleId);
        myStoredProcedureCall.setString(2, GameName);
        myStoredProcedureCall.setInt(3, Price);
        ResultSet myResults = myStoredProcedureCall.executeQuery();
        return myResults;
    }

    public ResultSet callSearchByGenre(String genreSearch) throws SQLException
    {
        CallableStatement myStoredProcedureCall = connection.prepareCall("{Call searchByGenre(?)}");
        myStoredProcedureCall.setString(1, genreSearch);
        ResultSet myResults = myStoredProcedureCall.executeQuery();
        return myResults;
    }
}