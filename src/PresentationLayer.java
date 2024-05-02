import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class PresentationLayer 
{
    private static DAL getDAL()
    {
        Scanner credentialScanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String userName = credentialScanner.nextLine();
        System.out.print("Enter password: ");
        Console console = System.console();
        char[] password = console.readPassword();
        return new DAL("VideogameShop", userName, new String(password));
    }

    public static void main(String[] args)
    {
        DAL dal = getDAL();
        System.out.println("Welcome to the VideoGame Shop! Here is the current inventory.");
        List<String> inventory = dal.getInventory();
        System.out.println("Game  ID" +  "Game Name" +  "Game Price" +  "Release Date" + "Game Genre");
        for(int i =0; i <= inventory.size(); i++)
        {
            System.out.println(inventory.indexOf(0) + inventory.indexOf(1) + "$" + inventory.indexOf(2) +  inventory.indexOf(3) + inventory.indexOf(4));
        }
    }
}
