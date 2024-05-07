import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class WorkflowFiveDal {

    private final Connection connection;

    public WorkflowFiveDal(Connection connection) throws SQLException {
        this.connection = connection;
    }

    protected int getGenreSelection() {
        try {
            try (var stm = connection.prepareStatement("select * from genre")) {
                System.out.println("Please choose a genre to search for, or any other value to back");
                try (var rs = stm.executeQuery()) {
                    while (rs.next()) {
                        System.out.printf("%d - %s\n", rs.getInt("id"), rs.getString("name"));
                    }
                }
            }
            System.out.println("Please type choice:");
            var optionScanner = new Scanner(System.in);
            return Integer.parseInt(optionScanner.nextLine());
        } catch (Exception ex) {
            // Nothing to do, just go back to the menu
        }
        return -1;
    }

    public void searchByGenre() {
        var genreId = getGenreSelection();
        if (genreId == -1) {
            return;
        }
        try (var stmt = connection.prepareCall("{call searchByGenre(?)}")) {
            stmt.setInt(1, genreId);
            try (var rs = stmt.executeQuery()) {
                System.out.printf("| %-50s | %-12s | %-10s | %-11s | %-12s |%n", "Name", "Release Date", "Quantity", "Price", "Genre");
                while (rs.next()) {
                    displayRow(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to search by genre");
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
