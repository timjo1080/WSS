package Items;
import Items.Trader.*;

//Use to compile
//javac Items/*.java Items/Trader/*.java Player/*.java Player/Vision/*.java
//Use to run
//java Items.TestTrader

public class TestTrader {
    public static void main(String[] args) {

        Offer offer = new Offer(10, 2, 1, 5, 3, 2);

        Trader t1 = new GenerousTrader("Nice Guy", true, 2);
        Trader t2 = new AngryTrader("Mad Guy", true, 2, 0);
        Trader t3 = new StupidTrader("Weird Guy", true);

        System.out.println("Original Offer:");
        System.out.println(offer);

        System.out.println("\nGenerous Trader:");
        System.out.println(t1.counterOffer(offer));

        System.out.println("\nAngry Trader:");
        System.out.println(t2.counterOffer(offer));

        System.out.println("\nStupid Trader:");
        System.out.println(t3.counterOffer(offer));
    }
    
}
