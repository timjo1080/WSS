package Items.Trader;
public class AngryTrader extends Trader {
    private int counterLimit;
    private int currentCounter;

    public AngryTrader(String name, boolean isRepeating, int counterLimit, int currentCounter) {
        super(name, isRepeating);
        this.counterLimit = counterLimit;
        this.currentCounter = currentCounter;
    }

    public boolean shouldDecline() {
        // TODO
        return false;
    }
}