import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;
import java.util.StringTokenizer;

public class WorkflowTwoDal 
{
    private final Connection connection;

    public WorkflowTwoDal(Connection connection) throws SQLException
    {
        this.connection = connection;
    }

    public void addToCustomer()
    {
        int cusID = getCustomerId() + 1;
        try
        {
            var customerInput = new Scanner(System.in);
            // System.out.println("Please enter a new customer id number.");
            System.out.println("Please type your full name");
            var fullName = customerInput.nextLine();
            var tokens = fullName.trim().split(" ");
            if (tokens.length != 2) {
                System.err.println("Invalid name provider, please provide your first and last name.");
            }
            try (var stmt = connection.prepareStatement("{call addToCustomer(?)}")) {
                stmt.setInt(1, cusID);
                stmt.setString(2, tokens[0].toUpperCase());
                stmt.setString(3, tokens[1].toUpperCase());
                try(var rs = stmt.executeQuery()) {
                    System.out.println("Success!");
                }
            }
        }
        catch (Exception ex) 
        {
        }
    } 

    public void addToSales()
    {
        int saleId = getSaleId() + 1;
        int customerId = getCustomerId() + 1;
        var gameScan = new Scanner(System.in);
        try
        {
            System.out.println("What game would you like to purchase? You can only purchase one copy of a game per customer.");
            var gameName = gameScan.nextLine();
            System.out.println("Please enter todays date.");
            var date = gameScan.nextLine();
            try(var ats = connection.prepareStatement("{call addToSales(?)}"))
            {
                ats.setInt(1, saleId);
                ats.setString(2, date);
                ats.setInt(3, customerId);
            }
        }
        catch (Exception ex) 
        {
        }
        
        while(true)
        {
            System.out.println("Would you like to purchase another game?");
            var purchaseAnother = gameScan.nextLine();
            if (purchaseAnother.equals("Yes"))
            {
                try
                {
                    System.out.println("What game would you like to purchase? You can only purchase one copy of a game per customer.");
                    var gameName = gameScan.nextLine();
                    System.out.println("Please enter todays date.");
                    var date = gameScan.nextLine();
                    try(var ats = connection.prepareStatement("{call addToSales(?)}"))
                    {
                        ats.setInt(1, saleId);
                        ats.setString(2, date);
                        ats.setInt(3, customerId);
                    }
                }
                catch (Exception ex) 
                {
                }
            }
            if(purchaseAnother.equals("No"))
            {
                break;
            }
        }

    }

    public void addToSaleDetails()
    {
        int price = getGamePrice();
        int saleId = getSaleDetailsId();
        int quantity = 1;
        int gameId = getGameId();
        try(var ats = connection.prepareStatement("{call addToSalesDetails(?)}"))
                    {
                        ats.setInt(1, saleId);
                        ats.setInt(2, gameId);
                        ats.setInt(3, quantity);
                        ats.setInt(4, price);
                    }
                
                catch (Exception ex) 
                {
                }
    }

    
    private int getCustomerId() {
        var customerId = -1;
        try {
            final String SQL = "SELECT MAX(Id) FROM Customer";
            try (var stmt = connection.prepareStatement(SQL)) {
                try(var rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        customerId = rs.getInt("Id");
                    }
                    if (customerId == -1) {
                        System.err.println("Not customer was found with the given Id.");
                    }
                }
            }
        } catch (Exception ex) {
            // Nothing to do, probably customer was not found
        }
        return customerId;
    }
    
    private int getSaleId() {
        var saleId = -1;
        try {
            final String SQL = "SELECT MAX(Id) FROM Sales";
            try (var stmt = connection.prepareStatement(SQL)) {
                try(var rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        saleId = rs.getInt("Id");
                    }
                    if (saleId == -1) {
                        System.err.println("Not customer was found with the given id.");
                    }
                }
            }
        } catch (Exception ex) {
            // Nothing to do, probably customer was not found
        }
        return saleId;
    }

    private int getSaleDetailsId() {
        var saleDetailsId = -1;
        try {
            final String SQL = "SELECT MAX(Id) FROM SalesDetails";
            try (var stmt = connection.prepareStatement(SQL)) {
                try(var rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        saleDetailsId = rs.getInt("SalesId");
                    }
                    if (saleDetailsId == -1) {
                        System.err.println("Not customer was found with the given id.");
                    }
                }
            }
        } catch (Exception ex) {
            // Nothing to do, probably customer was not found
        }
        return saleDetailsId;
    }

    private int getGameId() {
        var gameId = -1;
        try {
            final String SQL = "SELECT MAX(Id) FROM Game";
            try (var stmt = connection.prepareStatement(SQL)) {
                try(var rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        gameId = rs.getInt("Id");
                    }
                    if (gameId == -1) {
                        System.err.println("Not game was found with the given Id.");
                    }
                }
            }
        } catch (Exception ex) {
            // Nothing to do, probably customer was not found
        }
        return gameId;
    }
    
    private int getGamePrice() 
    {
        int gamePrice = 0;
        try {
            try (var stmt = connection.prepareStatement("call getGamePrice(?)")) {
                try(var rs = stmt.executeQuery()) {
                    
                        gamePrice = rs.getInt("Price");
                   
                }
            }
           
        } catch (Exception ex) {
            // Nothing to do, probably customer was not found
        }
        return gamePrice;
        
    }
    
}
