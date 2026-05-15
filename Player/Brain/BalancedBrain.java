package Player.Brain;

import Map.Map;
import Map.Terrain;
import Player.Player;
import Player.Vision.*;

// BalancedBrain will prioritize moving east until resources are less than half
// If low, will start looking for the nearest resource
// If none nearby, will look for a trader to trade with
public class BalancedBrain extends Brain {
    
    private boolean canMoveNextStep(Player player, Path path, int food, int water, int movementPts) {
        if (path == null || path.totalPath().isEmpty()) {
            return false;
        }

        Terrain terrain = getNextTerrain(player, path);
        
        return terrain != null && terrain.getMovementCost() <= movementPts && terrain.getWaterCost() <= water && terrain.getFoodCost() <= food;
    }

    private Terrain getNextTerrain(Player player, Path path) {
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

    public BalancedBrain(Vision vision, Map map) {
        super(vision, map);
    }

    public void makeMove(Player player) {
        int maxFood = player.getMaxFood();
        int maxWater = player.getMaxWater();

        int food = player.getCurrentFood();
        int water = player.getCurrentWater();
        int gold = player.getCurrentGold();
        int movementPts = player.getCurrentMovementPts();

        if (viablePath == null || viablePath.totalPath().isEmpty()) {
            pathList.clear();

            // put exisiting resource paths in pathList to be reevaluated
            // If all stats are good, attempt to move right
            if (water >= 0.5*maxWater && food >= 0.5*maxFood) {
                int newX = player.getPositionX() + 1;
                Terrain terrain = map.getTerrainAt(newX, player.getPositionY());
                
                // Move right if player has enough movement points
                if (terrain.getMovementCost() <= movementPts){
                    player.move("right", map.getWidth(), map.getHeight(), terrain.getMovementCost(), terrain.getWaterCost(), terrain.getFoodCost());
                    return;
                }
                else {
                    player.rest();
                    return;
                }
            }
            // If both resources low, prioritize lowest        
            else if(water <= 0.5*maxWater && food <= 0.5*maxFood){
                if(water < food){
                    addWaterPaths(pathList);
                    addFoodPaths(pathList);
                }
                else{
                    addFoodPaths(pathList);
                    addWaterPaths(pathList);
                }
                
                if(gold < 5){
                    addGoldPaths(pathList);
                }

                addTraderPaths(pathList);
                Path fallback = vision.fallbackPath();
                if (fallback != null) {
                    pathList.add(fallback);
                }
            }

            else{ 
                // If water is low, prioritize water
                if(water <= 0.5*maxWater) {
                    addWaterPaths(pathList);
                }

                // If food is low, prioritize food
                else if(food <= 0.5*maxFood) {
                    addFoodPaths(pathList);
                }

                if(gold < 5){
                    addGoldPaths(pathList);
                }
                addTraderPaths(pathList);
                Path fallback = vision.fallbackPath();
                if (fallback != null) {
                pathList.add(fallback);
                }
            }

            boolean possiblePathFound = false;
            while(!possiblePathFound && !pathList.isEmpty()){
                viablePath = pathList.remove(0);
                possiblePathFound = canMoveNextStep(player, viablePath, food, water, movementPts);
            }

            if(!possiblePathFound || viablePath == null || viablePath.totalPath().isEmpty()){
                player.rest();
                return;
            }
        }

        Terrain nextTerrain = getNextTerrain(player, viablePath);
        if (nextTerrain == null) {
            viablePath = null;
            return;
        }

        if (nextTerrain.getMovementCost() > movementPts) {
            player.rest();
            return;
        }

        if (nextTerrain.getWaterCost() > water || nextTerrain.getFoodCost() > food) {
            viablePath = null;
            return;
        }

        // If possible path was found, move
        // Makes a single move, and then will reevaluate the possible paths from the new position
        String direction = viablePath.totalPath().removeFirst(); 
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

        Terrain terrain = map.getTerrainAt(newX, newY);
        player.move(direction, map.getWidth(), map.getHeight(), terrain.getMovementCost(), terrain.getWaterCost(), terrain.getFoodCost());


        // This line of code, instead of making one move then reevaluting paths, will complete full path before picking a new path
        /*
        // Make moves one at a time until player reaches final position
        for (String direction : viablePath.totalPath()){
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

            Terrain terrain = map.getTerrainAt(newX, newY);
            player.move(direction, map.getWidth(), map.getHeight(), terrain.getMovementCost(), terrain.getWaterCost(), terrain.getFoodCost());

        }*/
    }   

}