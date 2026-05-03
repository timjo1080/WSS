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
        switch (type) {
            case "movement":
                movementCost -= amount;
                break;
            case "water":
                waterCost -= amount;
                break;
            case "food":
                foodCost -= amount;
                break;
        }
    }

    public boolean checkValues() {
        return movementCost >= 0 && waterCost >= 0 && foodCost >= 0;
    }

    public String getTerrainType() {
        return terrainType;
    }
}