import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkflowOneDal {

    private final Connection connection;

    public WorkflowOneDal(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void showStock() {
        try (var stmt = connection.prepareCall("{call showGamesInStock()}")) {
            try (var rs = stmt.executeQuery()) {
                System.out.printf("| %-50s | %-12s | %-10s | %-11s | %-12s |%n", "Name", "Release Date", "Quantity", "Price", "Genre");
                while (rs.next()) {
                    displayRow(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to show games in stock");
        }
    }

    private void displayRow(ResultSet rs) throws SQLException {
        System.out.printf(
                "| %-50s | %-12s | %-10s | %-11s | %-12s |%n",
                rs.getString("Name"), rs.getDate("ReleaseDate"),
                rs.getInt("Quantity"), String.format("%.2f", rs.getDouble("Price")),
                rs.getString("GenreName")
        );
    }

}
