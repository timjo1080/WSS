package Items;
import Map.Square;
import Player.Player;

public class Item {
    //vars
    private String name;
    private boolean isRepeating;

    //constructor
    public Item(String name, boolean isRepeating) {
        this.name = name;
        this.isRepeating = isRepeating;
    }

    public String getName() {
        return name;
    }

    public boolean isRepeating() {
        return isRepeating;
    }

    public void applyEffect(Player player, Item item, Square square) {
        if (name == "Food Pack") {
            player.addFood(Square.getFoodValue()); // Example effect: add 10 food to the player
        }
        else if (name == "Water Pack") {
            player.addWater(Square.getWaterValue()); // Example effect: add 10 water to the player
        }
        else if (name == "Gold Pack") {
            player.addGold(Square.getGoldValue()); // Example effect: add 10 gold to the player
        }
        else {
            System.out.println("Unknown item effect for " + name);
        }
        
        if (!item.isRepeating()) {
            square.removeItem(item);
            System.out.println(name + " is not a repeating item and has been used.");
        }
        else {
            System.out.println(name + " is a repeating item and can be used again.");
        }

        System.out.println(name + " effect applied to " + player.getClass().getSimpleName());
    }
}
