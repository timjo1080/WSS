import Map.Map;
import Player.Player;
import java.util.Scanner;
import Items.Trader.*;


public class WSS
{
    //vars
    private Map map;
    private Player player; 

    //constructor
    public WSS(int width, int height, String difficulty, Player player) {
        this.map = new Map(width, height, difficulty);
        this.player = player;
    }
    
    //methods
    public void startGame() {
         System.out.println("Game started!");

        if (player == null) {
        System.out.println("No player yet.");
        return;
        }

        displayStatus();

        }

    public void resetGame() {

    }

    public void displayStatus() {
        System.out.println("Displaying player status...");

    }

    public boolean checkWinOrLose() {
        return false; // placeholder
    }

    public void initializeMap() {

    }

    public void initializePlayer() {

    }

    public static void main(String[] args) {
    System.out.println("Starting WSS game...");

    Player player = new Player("Test", 100, 100, 100, 50, 50, 20, 10, null, null, 10, 0 ,0); // temporary until Player is finished

    WSS game = new WSS(10, 10, "normal", player);
    game.startGame();
    }

}