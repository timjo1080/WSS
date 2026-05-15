package Items;
import java.util.Scanner;

import Items.Trader.Offer;
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
        System.out.println("\n");
        if (name.equals("Food Pack")) {
            player.addFood(Square.getFoodValue()); // Example effect: add 10 food to the player
        }
        else if (name.equals("Water Pack")) {
            player.addWater(Square.getWaterValue()); // Example effect: add 10 water to the player
        }
        else if (name.equals("Gold Pack")) {
            player.addGold(Square.getGoldValue()); // Example effect: add 10 gold to the player
        }
        else if (item instanceof Items.Trader.Trader) {
            //need to implement offer
            Scanner scanner = new Scanner(System.in);
            
            Items.Trader.Trader trader = (Items.Trader.Trader) item;
            Offer playerOffer = new Offer(
                5, 0, 2,  
                0, 10, 0   
            );
            
            player.proposeTrade(playerOffer);

            Offer counter = trader.counterOffer(playerOffer);

            if (counter == null) {
                trader.declineOffer();
                player.rejectTrade();
            }
            else {
                System.out.println("Trader counters with: ");
                System.out.println(counter);

                System.out.println("Accept trade? (yes/no)");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                    trader.acceptOffer(counter);
                    player.acceptTrade(counter);
                }
                else{
                    trader.declineOffer();
                    player.rejectTrade();
                }
            }
        }
        else {
            System.out.println("Unknown item effect for " + name);
        }
        
        if (!item.isRepeating()) {
            System.out.println(name + " is not a repeating item and has been used.");
        }
        else {
            System.out.println(name + " is a repeating item and can be used again.");
        }

        System.out.println(name + " effect applied to " + player.getClass().getSimpleName());
        System.out.println("\n==================================");
        player.displayStatus();
        System.out.println("==================================\n");
    }
}
