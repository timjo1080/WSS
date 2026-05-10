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
        int maxStrength = player.getMaxStrength();

        int food = player.getCurrentFood();
        int water = player.getCurrentWater();
        int gold = player.getCurrentGold();
        int movementPts = player.getCurrentMovementPts();
        
        // If all stats are good, attempt to move right
        if (water > 0.25*maxWater && food > 0.25*maxFood) {
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
        else if(water <= 0.25*maxWater && food <= 0.25*maxFood){
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

        }

        else{ 
            // If water is low, prioritize water
            if(water <= 0.25*maxWater) {
                addWaterPaths(pathList);
            }

            // If food is low, prioritize food
            else if(food <= 0.25*maxFood) {
                addFoodPaths(pathList);
            }

            if(gold < 5){
                addGoldPaths(pathList);
            }
            addTraderPaths(pathList);
        }

        // Iterate through the given paths, and pick the first possible path
        boolean possiblePathFound = false;
        while(!possiblePathFound && !pathList.isEmpty()){
            viablePath = pathList.remove(0);
            possiblePathFound = isPossible(viablePath, food, water, movementPts);
        }

        // If no possible path was found, rest
        if(!possiblePathFound){
            player.rest();
            return;
        }

        // If possible path was found, move
        // Makes a single move, and then will reevaluate the possible paths from the new position
        String direction = viablePath.totalPath().getFirst(); 
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