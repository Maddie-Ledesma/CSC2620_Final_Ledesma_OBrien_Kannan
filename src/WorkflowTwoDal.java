import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class WorkflowTwoDal {
    private final Connection connection;

    public WorkflowTwoDal(Connection connection) throws SQLException {
        this.connection = connection;
    }

    protected int getCustomerId() {
        int cusID = -1;
        try {
            var customerInput = new Scanner(System.in);
            // System.out.println("Please enter a new customer id number.");
            System.out.println("Please type your full name");
            var fullName = customerInput.nextLine();
            var tokens = fullName.trim().split(" ");
            if (tokens.length != 2) {
                System.err.println("Invalid name provider, please provide your first and last name.");
            }
            try (var stmt = connection.prepareCall("{call getCustomerId(?, ?, ?)}")) {
                stmt.setString(1, tokens[0].trim().toUpperCase());
                stmt.setString(2, tokens[1].trim().toUpperCase());
                stmt.registerOutParameter(3, Types.INTEGER);
                stmt.execute();
                return stmt.getInt(3);
            }
        } catch (Exception ex) {
            System.out.println("Failed to find or create a customer");
            ex.printStackTrace();
        }
        return cusID;
    }

    protected String getGameName(Scanner scanner) {
        System.out.println("What game would you like to purchase?");
        return scanner.nextLine();
    }

    protected int getQuantity(Scanner scanner) {
        System.out.println("How many copies?");
        return scanner.nextInt();
    }

    public void purchaseGame() {
        var customerId = getCustomerId();
        System.out.println("customer id: " + customerId);
        if (customerId == -1) {
            return;
        }
        var gameScan = new Scanner(System.in);
        try {
            System.out.println("What game would you like to purchase?");
            var gameName = getGameName(gameScan);
            var copies = getQuantity(gameScan);
            try (var ats = connection.prepareCall("{call doPurchase(?,?,?,?)}")) {
                ats.setInt(1, customerId);
                ats.setString(2, gameName);
                ats.setInt(3, copies);
                ats.registerOutParameter(4, Types.INTEGER);
                ats.execute();
                var salesId = ats.getInt(4);
                if (salesId > 0) {
                    displayReceipt(salesId);
                } else if (salesId == -1) {
                    System.out.println("Failed to find or create the given customer name.");
                } else if (salesId == -2) {
                    System.out.println("either we don't have enough or ran out of stock.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Failed to purchase the game");
        }
    }

    private void displayReceipt(int salesId) throws SQLException {
        // Get customer and sales information
        final String SQL = "SELECT s.SalesDate, c.FirstName, c.LastName FROM Sales AS s "
        + "JOIN Customer AS c ON c.Id = s.CustomerId WHERE s.Id = ?";
        try(var pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, salesId);
            try(var rs = pstm.executeQuery()) {
                if(rs.next()) {
                    System.out.printf("Invoice ID: %d \t\t\t Date: %s%n", salesId, rs.getDate("SalesDate"));
                    System.out.printf("Customer: %s %s%n", rs.getString("FirstName"), rs.getString("LastName"));
                    displayDetails(salesId);
                }
            }
        }
    }

    private void displayDetails(int salesId) throws SQLException {
        final String SQL = "SELECT g.Name, sd.Quantity, sd.Price FROM SalesDetails AS sd "
                + "JOIN Game AS g ON g.id = sd.GameId WHERE sd.SalesId = ?";
        try(var pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, salesId);
            try(var rs = pstm.executeQuery()) {
                System.out.printf("| %-50s | %-10s | %-11s | %-12s |%n", "Name", "Quantity", "Price", "Total");
                while(rs.next()) {
                    var quantity = rs.getInt("Quantity");
                    var price = rs.getDouble("Price");
                    System.out.printf("| %-50s | %-10s | %-11s | %-12s |%n",
                            rs.getString("Name"), quantity,
                            String.format("%.2f", price), String.format("%.2f", price * quantity)
                    );
                }
            }
        }
    }
}
