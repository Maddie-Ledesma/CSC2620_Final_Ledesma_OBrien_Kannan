import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkflowFourDal {

    private final Connection connection;

    public WorkflowFourDal(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void ListPopularGames() {
        try (var stmt = connection.prepareCall("{call PopularGames()}")) {
            try (var rs = stmt.executeQuery()) {
                System.out.printf("| %-50s |%n", "Name");
                while (rs.next()) {
                    displayRow(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to list popular games");
        }
    }

    private void displayRow(ResultSet rs) throws SQLException {
        System.out.printf("| %-50s |%n", rs.getString("Name"));
    }

}
