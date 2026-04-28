package Items;
public class GoldBonus extends Item {
    private int quantity;

    public GoldBonus(String name, boolean isRepeating, int quantity) {
        super(name, isRepeating);
        this.quantity = quantity;
    }

    public void addGold() {
        // TODO
    }
}