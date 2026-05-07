package Player.Brain;
import Player.Player;
import Player.Vision.*;

public class Brain {
    Path viablePath;
    Vision vision;
    int mapWidth; // Maybe find a new way to get these variables later
    int mapHeight;

    // currently, need mapWidth and mapHeight to make moves due to how its coded
    public Brain(Vision vision, int mapWidth, int mapheight) {
        this.vision = vision;

    }

    public void makeMove(Player player) {
        int food = player.getCurrentFood();
        int water = player.getCurrentWater();
        int gold = player.getCurrentGold();
        int movementPts = player.getCurrentMovementPts();

        // Depending on brain type, prioritize certain options first. (EX. gluttonous brain will look for closest path to food, and take that)
        // Also check stats to see if the move is possible with the given stats, and if not, choose a differrnt path
        // Will call upon the brain's given vision to find the path 
        //
                
        for (String move : viablePath.totalPath()){
            player.move(move, mapWidth, mapHeight, 1, 1, 1 ); // Currently not sure how to get the width and height as it is right now
        }
    

    }
}