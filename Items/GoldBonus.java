package Items;

import Player.Player;

public class GoldBonus extends Item {
    private int quantity;

    public GoldBonus(String name, boolean isRepeating, int quantity) {
        super(name, isRepeating);
        this.quantity = quantity;
    }

    public void addGold(Player player) {
        player.addGold(quantity);
        System.out.println("Gained " + quantity + " gold! ");
    }
}
