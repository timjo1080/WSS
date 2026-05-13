import Map.Map;
import Map.Square;
import Player.Player;
import Player.Brain.BalancedBrain;
import Player.Brain.Brain;
import Player.Brain.SpeedyBrain;
import Player.Vision.CautiousVision;
import Player.Vision.FarSightVision;
import Player.Vision.FocusedVision;
import Player.Vision.KeenEyedVision;
import Player.Vision.Vision;

import java.util.Scanner;

import javax.swing.Box.Filler;

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

    //edit later to add vision and brain
    public void initializePlayer(String difficulty, Map map) {
        Scanner scanner = new Scanner(System.in);
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


        System.out.println("\n------------------------------");
        System.out.println("Choose your Vision:");
        System.out.println("------------------------------\n");
        System.out.println("  1. Cautious");
        System.out.println("  2. FarSight");
        System.out.println("  3. Focused");
        System.out.println("  4. KeenEyed");
        System.out.print("Enter choice (1-4): ");

        int visionChoice = scanner.nextInt();

        Vision vision = null;

        switch (visionChoice) {
            case 1:
                vision = new CautiousVision();
                System.out.println("\nVision set to Cautious!");
                break;

            case 2:
                vision = new FarSightVision();
                System.out.println("\nVision set to FarSight!");
                break;

            case 3:
                vision = new FocusedVision();
                System.out.println("\nVision set to Focused!");
                break;

            case 4:
                vision = new KeenEyedVision();
                System.out.println("\nVision set to KeenEyed!");
                break;

            default:
                vision = new FocusedVision();
                System.out.println("\nInvalid choice, defaulting to Focused.");
        }

        System.out.println("\n------------------------------");
        System.out.println("Choose your Brain:");
        System.out.println("------------------------------\n");
        System.out.println("  1. Balanced");
        System.out.println("  2. Speedy");
        System.out.println("  3. filler");
        System.out.print("Enter choice (1-3): ");

        int brainChoice = scanner.nextInt();

        Brain brain = null;

        switch (brainChoice) {

            case 1:
                brain = new BalancedBrain(vision, map);
                System.out.println("\nBrain set to Balanced!");
                break;

            case 2:
                brain = new SpeedyBrain(vision, map);
                System.out.println("\nBrain set to Speedy!");
                break;

            case 3:
                // brain = new Filler(vision, map);
                System.out.println("\nBrain set to Filler!");
                break;

            default:
                brain = new BalancedBrain(vision, map);
                System.out.println("\nInvalid choice, defaulting to Balanced.");
        }

        // Can modify the position and movement points
        this.player = new Player(
        "Player", maxStrength, maxWater, maxFood, 
        currentStrength, currentWater, currentFood, currentGold,
        vision, brain, 5, 0, 4); // make y position random

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
        scanner.nextLine();

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
        initializePlayer(difficulty, map);

        while(checkWinOrLose().equals("ongoing"))
        {
            displayStatus();
            map.getSquare(player.getPositionX(),player.getPositionY()).applyItems(player);
            System.out.println("\nPress ENTER to make your next move...");
            scanner.nextLine(); // wait for user input

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
        return "ongoing";
    }
}