package Items;
public class WaterBonus extends Item {
    private int quantity;

    public WaterBonus(String name, boolean isRepeating, int quantity) {
        super(name, isRepeating);
        this.quantity = quantity;
    }

    public void addWater() {
        // TODO
    }
}