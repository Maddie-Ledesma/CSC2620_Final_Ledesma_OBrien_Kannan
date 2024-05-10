import java.io.Console;
import java.sql.SQLException;
import java.util.Scanner;

public class PresentationLayer {

    private static ConnectionManager getConnectionManager() throws SQLException {
        Scanner credentialScanner = new Scanner(System.in);
        Console console = System.console();

        while(true)
        {
            System.out.print("Enter username: ");
            String userName = credentialScanner.nextLine();
            System.out.print("Enter password: ");
            char[] password = console.readPassword();
            ConnectionManager connectMan = new ConnectionManager("VideoGameShop", userName, new String(password));
            if(connectMan.isValid()){
                return connectMan;
            }
            System.err.println("Incorrect username or password.");
        }
    }

    public static void main(String[] args) throws SQLException {
        Scanner optionScanner = new Scanner(System.in);
        ConnectionManager connectionManager = getConnectionManager();

        String userOption = "";
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

            String userOption1 = optionScanner.nextLine();
            userOption = userOption1;
            if (!isValidOption(userOption)) {
                System.out.println("Invalid option, please select a valid option");
            } else {
                Integer value = Integer.parseInt(userOption);
                if (value == 1) {
                    WorkflowOneDal dal = new WorkflowOneDal(connectionManager.getConnection());
                    dal.showStock();
                } else if (value == 2){
                    WorkflowTwoDal dal = new WorkflowTwoDal(connectionManager.getConnection());
                    // call methods to purchase game
                    dal.purchaseGame();
                }
                else if (value == 3) {
                    WorkflowThreeDal dal = new WorkflowThreeDal(connectionManager.getConnection());
                    dal.showPurchasesHistory();
                } else if (value == 4) {
                    WorkflowFourDal dal = new WorkflowFourDal(connectionManager.getConnection());
                    dal.ListPopularGames();
                } else if (value == 5) {
                    WorkflowFiveDal dal = new WorkflowFiveDal(connectionManager.getConnection());
                    dal.searchByGenre();
                }
            }
        
        }
        optionScanner.close();
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
