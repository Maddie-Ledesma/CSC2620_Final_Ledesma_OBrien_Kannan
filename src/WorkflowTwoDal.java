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
        try (var pcr = connection.prepareCall("{call printCustomerReciept()}")) {
            try (var rs = pcr.executeQuery()) {
                System.out.printf("| %-12s | %-50s | %-12s |%n", "Sale Id", "Name","Price");
                while (rs.next()) {
                    displayRow(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to print customer's reciept");
        }
    }

    private void displayRow(ResultSet rs) throws SQLException
    {
        System.out.printf("| %-12s | %-50s | %-12s |%n", rs.getString("Sale Id"), rs.getString("Name"), String.format("%.2f", rs.getDouble("Price")));
    }
}
