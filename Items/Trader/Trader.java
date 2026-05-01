package Items.Trader;
import Items.Item;

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
}