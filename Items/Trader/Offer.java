package Items.Trader;
public class Offer {
    private int goldOffered;
    private int waterOffered;
    private int foodOffered;
    private int goldRequested;
    private int waterRequested;
    private int foodRequested;

    public Offer(int goldOffered, int waterOffered, int foodOffered,
                 int goldRequested, int waterRequested, int foodRequested) {
        this.goldOffered = goldOffered;
        this.waterOffered = waterOffered;
        this.foodOffered = foodOffered;
        this.goldRequested = goldRequested;
        this.waterRequested = waterRequested;
        this.foodRequested = foodRequested;
    }
}
