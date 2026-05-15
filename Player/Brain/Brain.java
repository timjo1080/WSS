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

    protected void addFallbackPath(ArrayList<Path> pathList){
        Path tempPath;
        tempPath = vision.fallbackPath();
        if (tempPath != null) {
            pathList.add(tempPath);
        }
    }

    protected boolean canMoveNextStep(Player player, Path path, int food, int water, int movementPts) {
        if (path == null || path.totalPath().isEmpty()) {
            return false;
        }
        Terrain terrain = getNextTerrain(player, path);
        return terrain != null && terrain.getMovementCost() <= movementPts && terrain.getWaterCost() <= water && terrain.getFoodCost() <= food;
    }

    protected boolean isPossible(Path path, int currFood, int currWater, int currMovement){
        return (path.getMovementCost() <= currMovement) && (path.getFoodCost() <= currFood) && (path.getWaterCost() <= currWater);
    }

    protected Terrain getNextTerrain(Player player, Path path) {
        if (path == null || path.totalPath().isEmpty()) {
            return null;
        }

        String direction = path.totalPath().getFirst(); 
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

        return map.getTerrainAt(newX, newY);
    }
}