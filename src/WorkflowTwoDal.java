import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class WorkflowTwoDal 
{
    private final Connection connection;

    public WorkflowTwoDal(Connection connection) throws SQLException
    {
        this.connection = connection;
    }

    public void printReciept(String customerSelection)
    {
        try (var stmt = connection.prepareCall("{call printCustomerReciept()}")) {
            try (var rs = stmt.executeQuery()) {
                System.out.printf("| %-50s | %-12s | %-10s | %-11s | %-12s |%n", "Name", "Release Date", "Quantity", "Price", "Genre");
                while (rs.next()) {
                    displayRow(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to print customer's reciept");
        }
    }

    private void displayRow(ResultSet rs) throws SQLException {

    }
}
