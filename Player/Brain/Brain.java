package Player.Brain;
import Player.Player;
import Player.Vision.*;

import java.util.ArrayList;

import Map.Map;
import Map.Terrain;

public class Brain {
    Vision vision;
    Map map;
    Path viablePath;
    ArrayList<Path> pathList = new ArrayList<>();

    public Brain(Vision vision, Map map) {
        this.vision = vision;
        this.map = map;
    }

    // The default method does nothing, as each subclass has their own implementation that should be called instead
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
            player.move(move, map.getWidth(), map.getHeight(), 1, 1, 1 ); // Currently not sure how to get the width and height as it is right now
        }
    }

    // Protected methods for subclasses to use
    protected void addWaterPaths(ArrayList<Path> pathList){
        Path tempPath;
        tempPath = vision.closestWater();
        if (tempPath != null) {
            pathList.add(tempPath);
        }

        tempPath = vision.secondClosestWater();
        if (tempPath != null) {
            pathList.add(tempPath);
        }
    }

    protected void addFoodPaths(ArrayList<Path> pathList){
        Path tempPath;
        tempPath = vision.closestFood();
        if (tempPath != null) {
            pathList.add(tempPath);
        }

        tempPath = vision.secondClosestFood();
        if (tempPath != null) {
            pathList.add(tempPath);
        }
    }

    protected void addTraderPaths(ArrayList<Path> pathList){
        Path tempPath;
        tempPath = vision.closestTrader();
        if (tempPath != null) {
            pathList.add(tempPath);
        }

        tempPath = vision.secondClosestTrader();
        if (tempPath != null) {
            pathList.add(tempPath);
        }
    }

    protected void addGoldPaths(ArrayList<Path> pathList){
        Path tempPath;
        tempPath = vision.closestGold();
        if (tempPath != null) {
            pathList.add(tempPath);
        }

        tempPath = vision.secondClosestGold();
        if (tempPath != null) {
            pathList.add(tempPath);
        }
    }

    protected boolean isPossible(Path path, int currFood, int currWater, int currMovement){
        return (path.getMovementCost() <= currMovement) && (path.getFoodCost() <= currFood) && (path.getWaterCost() <= currWater);
    }
    
}