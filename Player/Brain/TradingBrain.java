package Player.Brain;

import Map.Map;
import Map.Terrain;
import Player.Player;
import Player.Vision.*;

// TradingBrain will prioritize finding traders
// If gold is below 15, will look for gold
// If no traders are near, and stats are low (less than 25% of the max), will start looking for items
public class TradingBrain extends Brain {
    
    public TradingBrain(Vision vision, Map map) {
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
            // If all stats are good, look for traders
            if (water >= 0.25*maxWater && food >= 0.25*maxFood && gold >=15) {
                addTraderPaths(pathList);
                addFallbackPath(pathList);
            }

            // If stats good but gold is low, look for gold, or if none nearby, traders
            else if(water >= 0.25*maxWater && food >= 0.25*maxFood && gold < 15){
                addGoldPaths(pathList);
                addTraderPaths(pathList);
                addFallbackPath(pathList);
            }
            
            // If both resources low, but gold is good, look for traders, and if none nearby, look for
            // resources, prioritizing the lowest stat
            else if(water < 0.25*maxWater && food < 0.25*maxFood && gold >=15){
                addTraderPaths(pathList);
                if(food < water){
                    addFoodPaths(pathList);
                    addWaterPaths(pathList);
                }
                else{
                    addWaterPaths(pathList);
                    addFoodPaths(pathList);
                }
                addFallbackPath(pathList);
            }

            // If only water or food is low (but not both), and gold is good, look for traders
            // and if none nearby, look for the lowest resource item
            else if((water < 0.25*maxWater || food < 0.25*maxFood) && gold >=15){
                addTraderPaths(pathList);
                // If water is low, prioritize water
                if(water < 0.25*maxWater) {
                    addWaterPaths(pathList);
                }
                // If food is low, prioritize food
                else if(food < 0.25*maxFood) {
                    addFoodPaths(pathList);
                }
                addFallbackPath(pathList);
            }

            // If everything is low, look for gold first, then traders, then items
            else if(water < 0.25*maxWater && food < 0.25*maxFood && gold < 15){
                addGoldPaths(pathList);
                addTraderPaths(pathList);
                if(food < water){
                    addFoodPaths(pathList);
                    addWaterPaths(pathList);
                }
                else{
                    addWaterPaths(pathList);
                    addFoodPaths(pathList);
                }
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
        
        player.move(direction, map.getWidth(), map.getHeight(), nextTerrain.getMovementCost(), nextTerrain.getWaterCost(), nextTerrain.getFoodCost());
    }   

}