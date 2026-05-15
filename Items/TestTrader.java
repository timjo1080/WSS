package Items;

import java.util.Scanner;
import Items.Trader.*;
import Player.Player;

//Use to compile
//javac Items/*.java Items/Trader/*.java Player/*.java Player/Vision/*.java
//Use to run
//java Items.TestTrader

public class TestTrader {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

         Player player = new Player(
            "TestPlayer",
            100, 100, 100,
            50, 50, 20, 30,
            null, null,
            5, 0, 0
        );

        Trader trader = new GenerousTrader("Nice Trader", true, 2);

        Offer playerOffer = new Offer(
            5, 0, 2,  
            0, 10, 0   
        );

        System.out.println(player.getName() + " proposes: " + playerOffer);

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

        player.displayStatus();
        scanner.close();
    }
    
}
