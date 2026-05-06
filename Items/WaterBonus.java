package Items;

import Player.Player;

public class WaterBonus extends Item {
    private int quantity;

    public WaterBonus(String name, boolean isRepeating, int quantity) {
        super(name, isRepeating);
        this.quantity = quantity;
    }

    public void addWater(Player player) {
        player.addFood(quantity);
        System.out.println("Gained " + quantity + " water! ");
    }
}
