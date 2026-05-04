package Items;

import Player.Player;

public class FoodBonus extends Item {
    private int quantity;

    public FoodBonus(String name, boolean isRepeating, int quantity) {
        super(name, isRepeating);
        this.quantity = quantity;
    }

    public void addFood(Player player) {
        player.addFood(quantity);
        System.out.println("Gained " + quantity + " food! ");
    }
}