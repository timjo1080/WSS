package Items.Trader;
import java.util.Scanner;

import Items.Item;
import Player.Player;

public class Trader extends Item {

    public Trader(String name, boolean isRepeating) {
        super(name, isRepeating);
    }

    public Offer counterOffer(Offer offer) {
        
        return new Offer(
            offer.getGoldOffered(),
            offer.getWaterOffered(),
            offer.getFoodOffered(),
            offer.getGoldRequested(),
            offer.getWaterRequested(),
            offer.getFoodRequested()

        );
    }
// will add more when we have resources sorted
    public void acceptOffer(Offer offer) {
        System.out.println(getClass().getSimpleName() + " accepts the offer: " +
            offer.getGoldOffered() + " gold, " +
            offer.getWaterOffered() + " water, " +
            offer.getFoodOffered() + " food, in exchange for " +
            offer.getGoldRequested() + " gold, " +
            offer.getWaterRequested() + " water, " + 
            offer.getFoodRequested() + " food." 
        );
    }

    public void declineOffer() {
        System.out.println(getClass().getSimpleName() + " declines the offer.");
    }

    /**
     * placeholder
     * @return
     */
    public String think(){
        if (Math.random() < 0.25) {
            return "accept";
        } 
        else if (Math.random() < 0.5) {
            return "counter";
        } 
        else {
            return "decline";
        }
    }

    public void trading(Player player)
    {
        while(true)
        {
            Offer playerOffer = player.proposeTrade(); // player enters their proposed trade

            String traderResponse = think(); // trader either accepts, declines, counters
            if(traderResponse.equals("accepts"))
            {
                acceptOffer(playerOffer);
                break;
            }
            else if(traderResponse.equals("decline"))
            {
                declineOffer();
                break;
            }

            counterOffer(playerOffer);

            String playerResponse = player.think(); // player either accepts, declines, counters
            if(traderResponse.equals("accepts"))
            {
                player.acceptTrade(playerOffer);
                break;
            }
            else if(traderResponse.equals("decline"))
            {
                player.declineTrade();
                break;
            }
            
            player.proposeTrade();
        }
    }
}