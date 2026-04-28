package Map;
public class Terrain {
    private String terrainType;
    private int movementCost;
    private int waterCost;
    private int foodCost;

    public Terrain(String terrainType, int movementCost, int waterCost, int foodCost) {
        this.terrainType = terrainType;
        this.movementCost = movementCost;
        this.waterCost = waterCost;
        this.foodCost = foodCost;
    }

    public void subtractValue(int amount, String type) {
        // TODO
    }

    public boolean checkValues() {
        // TODO
        return false;
    }
}