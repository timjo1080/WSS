package Items.Trader;
public class GenerousTrader extends Trader {
    private int generosityLevel;

    public GenerousTrader(String name, boolean isRepeating, int generosityLevel) {
        super(name, isRepeating);
        this.generosityLevel = generosityLevel;
    }
}
