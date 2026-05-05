package Items.Trader;

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
        currentCounter++;

        if (shouldDecline()) {
            declineOffer();
            return null;

        }
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
}