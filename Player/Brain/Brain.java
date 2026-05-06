package Player.Brain;
import Player.Player;
import Player.Vision.*;
import Map.Map;

public class Brain {
    Path viablePath;
    Vision vision;
    Map map;
    int mapWidth; // Maybe find a new way to get these variables later
    int mapHeight;

    // currently, need mapWidth and mapHeight to make moves due to how its coded
    public Brain(Vision vision, Map map) {
        this.vision = vision;
        this.map = map;

    }

    public void makeMove(Player player) {
        int food = player.getCurrentFood();
        int water = player.getCurrentWater();
        int gold = player.getCurrentGold();
        int movementPts = player.getCurrentMovementPts();

        // Depending on brain type, prioritize certain options first. (EX. gluttonous brain will look for closest path to food, and take that)
        // Also check stats to see if the move is possible with the given stats, and if not, choose a differrnt path
        // Will call upon the brain's given vision to find the path 
        viablePath = vision.easiestPath(player, map);
                
        for (String move : viablePath.totalPath()){
            player.move(move, map.getMapHeight(), map.getMapHeight()); // Currently not sure how to get the width and height as it is right now
        }
    

    }
}