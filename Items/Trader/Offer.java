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
        //finished the getters
    }
    public int getGoldOffered() {
        return goldOffered;

    }
    public int getWaterOffered() {
        return waterOffered;

    }
    public int getGoldRequested(){
        return goldRequested;
    
    }
    public int getFoodOffered() {
        return foodOffered;

    }
    public int getWaterRequested() {
        return waterRequested;

    }
    public int getFoodRequested() {
        return foodRequested;
    }

}
