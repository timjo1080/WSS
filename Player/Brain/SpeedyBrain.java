package Player.Brain;

import Map.Map;
import Map.Terrain;
import Player.Player;
import Player.Vision.*;

// SpeedyBrain will prioritize moving east until resources are low
// If low, will start looking for the nearest resource
// If none nearby, will look for a trader to trade with
public class SpeedyBrain extends Brain {
    
    public SpeedyBrain(Vision vision, Map map) {
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

            // Put exisiting resource paths in pathList to be reevaluated
            // If all stats are good, attempt to move right
            if (water >= 0.25*maxWater && food >= 0.25*maxFood) {
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
            else if(water < 0.25*maxWater && food < 0.25*maxFood){
                if(food < water){
                    addFoodPaths(pathList);
                    addWaterPaths(pathList);
                }
                else{
                    addWaterPaths(pathList);
                    addFoodPaths(pathList);
                }
                
                if(gold < 10){
                    addGoldPaths(pathList);
                }
                addTraderPaths(pathList);
                addFallbackPath(pathList);
            }

            // Only one of the stats is low
            else{ 
                // If water is low, prioritize water
                if(water < 0.25*maxWater) {
                    addWaterPaths(pathList);
                }

                // If food is low, prioritize food
                else if(food < 0.25*maxFood) {
                    addFoodPaths(pathList);
                }

                // If gold is low, get gold
                if(gold < 10){
                    addGoldPaths(pathList);
                }
                addTraderPaths(pathList);
                addFallbackPath(pathList);
            }

            // Iterate through the given paths, and pick the first possible path that has a square that can be moved into
            boolean possiblePathFound = false;
            while(!possiblePathFound && !pathList.isEmpty()){
                viablePath = pathList.remove(0);
                possiblePathFound = canMoveNextStep(player, viablePath, food, water, movementPts);
            }

            // If no possible path was found, rest
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

        player.move(direction, map.getWidth(), map.getHeight(), nextTerrain.getMovementCost(), nextTerrain.getWaterCost(), nextTerrain.getFoodCost());
    }   

}