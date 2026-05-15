package Player.Brain;
import Player.Player;
import Player.Vision.*;

import java.util.ArrayList;

import Map.Map;
import Map.Terrain;

// Superclass for the subclasses
// Containes methods that the subclasses can use
// Brain subclasses responsible for determining the
// player's next move automatically
public class Brain {
    Vision vision;
    Map map;
    Path viablePath;
    ArrayList<Path> pathList = new ArrayList<>();

    public Brain(Vision vision, Map map) {
        this.vision = vision;
        this.map = map;
    }

    // The make move method for the Brain class throws an exception, since
    // the subclasses should be implementing and overriding this method
    // The player should not be using this default Brain, only the subclasses
    public void makeMove(Player player) {
        throw new UnsupportedOperationException();
    }

    // Protected methods for subclasses to use
    // The following methods adds certain types of paths to the pathList
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

    // Checks if the player can actually perform the next move, based on their current stats
    protected boolean canMoveNextStep(Player player, Path path, int food, int water, int movementPts) {
        if (path == null || path.totalPath().isEmpty()) {
            return false;
        }
        Terrain terrain = getNextTerrain(player, path);
        return terrain != null && terrain.getMovementCost() <= movementPts && terrain.getWaterCost() <= water && terrain.getFoodCost() <= food;
    }

    // Gets the next terrain that the player will be in if they move
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