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
        // TODO
        return null;
    }

    public ArrayList<Integer> totalCost() {
        // TODO
        return null;
    }

    public int getMovementCost(){
        return movementCost;
    }

    public int getWaterCost(){
        return waterCost;
    }

    public int getFoodCost(){
        return movementCost;
    }
}