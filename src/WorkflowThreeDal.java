import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WorkflowThreeDal {

    private final Connection connection;

    public WorkflowThreeDal(Connection connection) throws SQLException {
        this.connection = connection;
    }

    protected int getCustomerId() {
        var customerId = -1;
        try {
            var customerScanner = new Scanner(System.in);
            System.out.println("Please type your full name");
            var fullName = customerScanner.nextLine();
            var tokens = fullName.trim().split(" ");
            if (tokens.length != 2) {
                System.err.println("Invalid name provider, please provide your first and last name.");
                return customerId;
            }
            final String SQL = "SELECT Id FROM Customer WHERE UPPER(FirstName) = ? AND UPPER(LastName) = ?";
            try (var stmt = connection.prepareStatement(SQL)) {
                stmt.setString(1, tokens[0].toUpperCase());
                stmt.setString(2, tokens[1].toUpperCase());
                try(var rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        customerId = rs.getInt("Id");
                    }
                    if (customerId == -1) {
                        System.err.println("Not customer was found with the given full name.");
                    }
                }
            }
        } catch (Exception ex) {
            // Nothing to do, probably customer was not found
        }
        return customerId;
    }
    public void showPurchasesHistory() {
        var customerId = getCustomerId();
        if ( customerId == -1) {
            return;
        }
        try (var stmt = connection.prepareCall("{call TrackCustomerPurchases(?)}")) {
            stmt.setInt(1, customerId);
            try (var rs = stmt.executeQuery()) {
                System.out.printf("| %-50s | %-12s | %-10s | %-11s | %-12s |%n", "Name", "Sales Date", "Quantity", "Price", "Genre");
                while (rs.next()) {
                    displayRow(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to show purchase history");
        }
    }

    private void displayRow(ResultSet rs) throws SQLException {
        System.out.printf(
                "| %-50s | %-12s | %-10s | %-11s | %-12s |%n",
                rs.getString("Name"), rs.getDate("SalesDate"),
                rs.getInt("Quantity"), String.format("%.2f", rs.getDouble("Price")),
                rs.getString("GenreName")
        );
    }

}
