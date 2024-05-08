import java.io.Console;
import java.sql.SQLException;
import java.util.Scanner;

public class PresentationLayer {
    private static ConnectionManager getConnectionManager() throws SQLException {
        Scanner credentialScanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String userName = credentialScanner.nextLine();
        System.out.print("Enter password: ");
        Console console = System.console();
        char[] password = console.readPassword();
        return new ConnectionManager("VideoGameShop", userName, new String(password));
    }

    public static void main(String[] args) throws SQLException {
        ConnectionManager connectionManager = getConnectionManager();
        var userOption = "";
        while (!userOption.equals("6")) {
            System.out.println();
            System.out.println("===============================================");
            System.out.println("        Welcome to the VideoGame Shop!");
            System.out.println("===============================================");
            System.out.println("1.- List Games in Stock");
            System.out.println("2.- Purchase Game");
            System.out.println("3.- List Purchase History by Customer");
            System.out.println("4.- List Popular Games");
            System.out.println("5.- Search by Genre");
            System.out.println("6.- Exit");
            System.out.println("Please select a choice");
            var optionScanner = new Scanner(System.in);
            userOption = optionScanner.nextLine();
            if (!isValidOption(userOption)) {
                System.out.println("Invalid option, please select a valid option");
            } else {
                var value = Integer.parseInt(userOption);
                if (value == 1) {
                    var dal = new WorkflowOneDal(connectionManager.getConnection());
                    dal.showStock();
                } else if (value == 2){
                    var dal = new WorkflowTwoDal(connectionManager.getConnection());
                    // call methods to purchase game
                    dal.purchaseGame();
                }
                else if (value == 3) {
                    var dal = new WorkflowThreeDal(connectionManager.getConnection());
                    dal.showPurchasesHistory();
                } else if (value == 4) {
                    var dal = new WorkflowFourDal(connectionManager.getConnection());
                    dal.ListPopularGames();
                } else if (value == 5) {
                    var dal = new WorkflowFiveDal(connectionManager.getConnection());
                    dal.searchByGenre();
                }
            }
        }
    }

    private static boolean isValidOption(String value) {
        try {
            var option = Integer.parseInt(value);
            return option >= 1 && option <= 6;
        } catch (Exception ex) {
            return false;
        }
    }
}
