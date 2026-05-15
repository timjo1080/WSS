package Items.Trader;

import java.util.Random;

import Player.Player;

public class StupidTrader extends Trader {

    private Random rand = new Random();

    public StupidTrader(String name, boolean isRepeating) {
        super(name, isRepeating);
    }

    /**
     * This method processes the player's offer. This trader cannot counteroffer but coin flips whether or not to accept.
     * @return
     */
    public String think(){
        if (Math.random() < 0.5) {
            return "accept";
        } else {
            return "decline";
        }
    }

    public void trading(Player player) {
        Offer playerOffer = player.proposeTrade(); // player enters their proposed trade

            String traderResponse = think(); // trader either accepts, declines, counters
            if(traderResponse.equals("accepts"))
            {
                acceptOffer(playerOffer);
            }
            else if(traderResponse.equals("decline"))
            {
                declineOffer();
            }

    }
}