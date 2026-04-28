package Items;
public class FoodBonus extends Item {
    private int quantity;

    public FoodBonus(String name, boolean isRepeating, int quantity) {
        super(name, isRepeating);
        this.quantity = quantity;
    }

    public void addFood() {
        // TODO
    }
}