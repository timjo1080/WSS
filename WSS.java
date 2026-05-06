import Map.Map;
import Player.Player;
import java.util.Scanner;

public class WSS
{
    //vars
    private Map map;
    private Player player; 

    public WSS() {
    }

    //constructor
    public WSS(int width, int height, String difficulty, Player player) {
        this.map = new Map(width, height, difficulty);
        this.player = player;
    }


    //getters
    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    //setters
    public void initializeMap(int width, int height, String difficulty) {
        this.map = new Map(width, height, difficulty);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    //methods
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=================================================");
        System.out.println();
        System.out.println("Welcome to Wilderness Survival System");
        System.out.println();
        System.out.println("=================================================");

        // setting map size
        System.out.print("\nEnter map width (Ex: 10): ");
        int width = scanner.nextInt();
        while (width <= 0) {
            System.out.print("Invalid! Enter a positive width: ");
            width = scanner.nextInt();
        }

        System.out.print("Enter map height (Ex: 10): ");
        int height = scanner.nextInt();
        while (height <= 0) {
            System.out.print("Invalid! Enter a positive height: ");
            height = scanner.nextInt();
        }

        System.out.println("\n\nSet to " + width + "x" + height + "!");
    
        // setting difficulty
        System.out.println("\n------------------------------");
        System.out.println("Select difficulty:");
        System.out.println("------------------------------");
        System.out.println("  1. Easy");
        System.out.println("  2. Medium");
        System.out.println("  3. Hard");
        System.out.print("Enter choice (1-3): ");
        int diffOption = scanner.nextInt();

        String difficulty = "Medium";
        switch (diffOption) {
            case 1: 
                difficulty = "Easy"; 
                System.out.println("Set to Easy!");
                break;
            case 2: 
                difficulty = "Medium"; 
                System.out.println("Set to Medium!");
                break;
            case 3: 
                difficulty = "Hard"; 
                System.out.println("Set to Hard!");
                break;
            default:
                System.out.println("Invalid choice, defaulting to Medium.");
        }
        
        System.out.println("\nIntializing Map...");
        initializeMap(width, height, difficulty);
        System.out.println("Initalizing Player...\n\n");
        initializePlayer();

        while(checkWinOrLose().equals("ongoing"))
        {
            makeNextMove();
        }
        if(checkWinOrLose().equals("win"))
        {
            System.out.println("The player got to the end!\n\n");
        }
        else 
        {
            System.out.println("The player did not make it to the end.\n\n");
        }

    }

    public void makeNextMove()
    {
        player.getBrain().makeMove(player);
    }

    public void displayStatus() {

    }

    public String checkWinOrLose() {
        return "win"; // placeholder (win, ongoing, lost)
    }

    public void initializePlayer() {

    }

}