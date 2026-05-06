package Player.Vision;
import Player.Player;
import java.util.ArrayList;

import Map.Map;
import Map.Square;

public class Vision {
    private ArrayList<Square> visiblility;

    public Vision() {
        this.visiblility = new ArrayList<>();
    }

    public Path closestFood() {
        // TODO
        return null;
    }

    public Path closestWater() {
        // TODO
        return null;
    }

    public Path closestGold() {
        // TODO
        return null;
    }

    public Path closestTrader() {
        // TODO
        return null;
    }

    // TEMPRARY CODE!!!!!!!!!!
    // Working on this to test if Brain works for the time being
    // Assuming cautious vision, can see square above, right, and below
    // Player starts at (0,0)/bottom left (x,y)
    public Path easiestPath(Player player, Map map) {
        Path path;
        ArrayList<String> moves = new ArrayList<>();
        int currentX = player.getCurrentX();
        int currentY = player.getCurrentY();

        visiblility.add(map.getSquareAt(currentX, currentY+1)); // Add square above
        visiblility.add(map.getSquareAt(currentX, currentY-1)); // Add square below
        visiblility.add(map.getSquareAt(currentX+1, currentY)); // Add square to right

        Square bestSquare = null;
        int lowestMovement = 0;
        for (Square square : visiblility) {
            if(square != null){
                if(bestSquare == null) {
                    bestSquare = square;
                    lowestMovement = square.getTerrain().getTerrainMovementCost();
                }
                else {
                    if(square.getTerrain().getTerrainMovementCost() < lowestMovement) { // Get the square with the lowest movement cost
                        bestSquare = square;
                        lowestMovement = square.getTerrain().getTerrainMovementCost();
                    }
                }
            }
        }
        if (bestSquare.getPositionX() > currentX){
            moves.add("right");
        }
        else if(bestSquare.getPositionY() > currentY){
            moves.add("down");
        }
        else {
            moves.add("up");
        }
                    
        path = new Path(lowestMovement, bestSquare.getTerrain().getTerrainWaterCost(), bestSquare.getTerrain().getTerrainFoodCost(), moves); //movement, water cost, food cost, moves list

        return path;
    }

    public Path secondClosestFood() {
        // TODO
        return null;
    }

    public Path secondClosestWater() {
        // TODO
        return null;
    }

    public Path secondClosestGold() {
        // TODO
        return null;
    }

    public Path secondClosestTrader() {
        // TODO
        return null;
    }
}
