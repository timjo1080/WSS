package Items.Trader;

import Player.Player;

public class GenerousTrader extends Trader {
    private int generosityLevel;
    private int offerCount = 0;

    public GenerousTrader(String name, boolean isRepeating, int generosityLevel) {
        super(name, isRepeating);
        this.generosityLevel = generosityLevel;
    }
// Generosity Level directly affects the amount of resources reduced for trade
    public Offer counterOffer(Offer offer) {
        int newGoldOffer = Math.max(0, offer.getGoldRequested() + generosityLevel);
        int newWaterOffer = Math.max(0, offer.getWaterRequested() + generosityLevel);
        int newFoodOffer = Math.max(0, offer.getFoodRequested() + generosityLevel);


//new offered reduced by generosity level
        return new Offer(
            newGoldOffer,
            newWaterOffer,
            newFoodOffer,
            offer.getGoldOffered(),     // these are what the player offered.
            offer.getWaterOffered(),
            offer.getFoodOffered()

        );

    }

    /**
     * This trader always counters with more resources. After 3 back and forths, he gets tired and just accepts whatever.
     * @return
     */
    public String think()
    {
        offerCount++;
        if(offerCount > 3)
        {
            return "accepts";
        }
        else
        {
            return "counter";
        }
    }

    public void trading(Player player)
    {
        offerCount = 0;
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

            System.out.println("Trader Counters your Offer with:");
            Offer counterOffer = counterOffer(playerOffer);
            System.out.println(counterOffer);

            String playerResponse = player.think(); // player either accepts, declines, counters
            if(playerResponse.equals("accepts"))
            {
                player.acceptTrade(counterOffer);
                break;
            }
            else if(playerResponse.equals("decline"))
            {
                player.declineTrade();
                break;
            }
        }
    }
}
