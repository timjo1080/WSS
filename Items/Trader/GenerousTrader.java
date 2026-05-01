package Items.Trader;
public class GenerousTrader extends Trader {
    private int generosityLevel;

    public GenerousTrader(String name, boolean isRepeating, int generosityLevel) {
        super(name, isRepeating);
        this.generosityLevel = generosityLevel;
    }
// Generosity Level directly affects the amount of resources reduced for trade
    public Offer counterOffer(Offer offer) {
        int newGoldRequested = Math.max(0, offer.getGoldRequested() - generosityLevel);
        int newWaterRequested = Math.max(0, offer.getWaterRequested() - generosityLevel);
        int newFoodRequested = Math.max(0, offer.getFoodRequested() - generosityLevel);

        return new Offer(
            offer.getGoldOffered(),
            offer.getWaterOffered(),
            offer.getFoodOffered(),
            newGoldRequested,
            newWaterRequested,
            newFoodRequested

        );

    }
}
