import Map.Map;
import Player.Player;

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

    }

    public void resetGame() {

    }

    public void displayStatus() {

    }

    public boolean checkWinOrLose() {
        return false; // placeholder
    }

    public void initializeMap() {

    }

    public void initializePlayer() {

    }

}