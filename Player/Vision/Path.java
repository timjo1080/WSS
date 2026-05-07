package Player.Vision;
import java.util.ArrayList;

public class Path {
    private int movementCost;
    private int waterCost;
    private int foodCost;
    private ArrayList<String> moves;

    public Path(int movementCost, int waterCost, int foodCost) {
        this.movementCost = movementCost;
        this.waterCost = waterCost;
        this.foodCost = foodCost;
        this.moves = new ArrayList<>();
    }

    public ArrayList<String> totalPath() {
        return moves;
    }

    public ArrayList<Integer> totalCost() {

        //Calculates cost of each item in list 'costs'
        //Example: if movementCost = 2, waterCost = 3, foodCost = 1, then costs will be [2, 3, 1]
        ArrayList<Integer> costs = new ArrayList<>();
        costs.add(movementCost);
        costs.add(waterCost);
        costs.add(foodCost);
        return costs;
    }

    public int getTotalCost() {
        return movementCost + waterCost + foodCost;
    }

    public int getMovementCost(){
        return movementCost;
    }

    public int getWaterCost(){
        return waterCost;
    }

    public int getFoodCost(){
        return foodCost;
    }
}
