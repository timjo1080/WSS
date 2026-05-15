package Items.Trader;

import Player.Player;

public class AngryTrader extends Trader {
    private int counterLimit;
    private int currentCounter;

    //Notes
//right now it starts with a counter of 0 but 
//we could make it so a random trader is generated already angry

    public AngryTrader(String name, boolean isRepeating, int counterLimit, int currentCounter) {
        super(name, isRepeating);
        this.counterLimit = counterLimit;
        this.currentCounter = currentCounter;
    }

    public boolean shouldDecline() {
       return currentCounter >= counterLimit;

    }

    public Offer counterOffer(Offer offer) {
//When angry requests more resources 
        return new Offer(
            offer.getGoldOffered(),
            offer.getWaterOffered(),
            offer.getFoodOffered(),
            offer.getGoldRequested() + 2,
            offer.getWaterRequested() + 2,
            offer.getFoodRequested() + 2
        );
    }

    public String think(Offer playerOffer)
    {
        currentCounter++;
        if(currentCounter > counterLimit)
        {
            return "decline";
        }
        else if(playerOffer.getGoldRequested() < 3 && playerOffer.getFoodRequested() < 3 && playerOffer.getWaterRequested() < 3)
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
        currentCounter = 0;
        while(true)
        {
            Offer playerOffer = player.proposeTrade(); // player enters their proposed trade

            String traderResponse = think(playerOffer); // trader either accepts, declines, counters
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

            System.out.println("Trader Counters your Offer With:");
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