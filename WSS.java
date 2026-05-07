import Map.Map;
import Player.Player;
import java.util.Scanner;
import Items.Trader.*;
import Map.Terrain;


public class WSS
{
    //vars
    private Map map;
    private Player player; 
    private Scanner scanner = new Scanner(System.in);

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

    public void initializePlayer(String difficulty) {
        int maxStrength = 100;
        int maxWater = 100;
        int maxFood = 100;

        int currentStrength;
        int currentWater;
        int currentFood;
        int currentGold;

        if (difficulty.equalsIgnoreCase("Easy")) {
            currentStrength = 100;
            currentWater = 100;
            currentFood = 100;
            currentGold = 50;

        } 
        else if (difficulty.equalsIgnoreCase("Medium")) {
            currentStrength = 75;
            currentWater = 75;
            currentFood = 75;
            currentGold = 25;
        }
        else{
            currentStrength = 50;
            currentWater = 50;
            currentFood = 50;
            currentGold = 10;
        }


        // Can modify the position and movement points
        this.player = new Player(
        "Player", maxStrength, maxWater, maxFood, 
        currentStrength, currentWater, currentFood, currentGold,
        null, null, 5, 4, 4);

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
        map.createMap();
        System.out.println("Initalizing Player...\n\n");
        initializePlayer(difficulty);

        while(checkWinOrLose().equals("ongoing"))
        {
            displayStatus();
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
        if (player.getBrain() == null) {
            System.out.print("Enter move direction (up/down/left/right/stay):");
            String direction = scanner.next().toLowerCase();

            if (direction.equals("stay")){
                player.rest();
                return;
            }
            int newX = player.getPositionX();
            int newY = player.getPositionY();

            if (direction.equals("up")) {
                newY -= 1;
            } else if (direction.equals("down")) {
                newY += 1;
            } else if (direction.equals("left")) {
                newX -= 1;
            } else if (direction.equals("right")) {
                newX += 1;
            }
            else{
                System.out.println("Invalid direction brochaco.");
                return;
            }

            Terrain terrain = map.getTerrainAt(newX, newY);

            if (terrain == null) {
                System.out.println("You cannot move off the map.");
                return;
            }
            player.move(direction, map.getWidth(), map.getHeight(), terrain.getMovementCost(), terrain.getWaterCost(), terrain.getFoodCost());
            return;
        }
        player.getBrain().makeMove(player);
    }

    public void displayStatus() {
        System.out.println("Displaying map and player status...\n");
        map.displayMap(player.getPositionX(), player.getPositionY());
        System.out.println("\n\n");
        player.displayStatus();
    

        Terrain terrain = map.getTerrainAt(player.getPositionX(), player.getPositionY());
        System.out.println("Current terrain: " + terrain.getTerrainType());        

    }
    

    public String checkWinOrLose() {
        if (player.getPositionX() == map.getWidth() - 1) {
        return "win";
        }
        if (player.getCurrentStrength() <= 0 ||
            player.getCurrentWater() <= 0 ||
            player.getCurrentFood() <= 0) {
            return "lost";
        
            
        }
        return "ongoing"; // placeholder (win, ongoing, lost)
    }
}