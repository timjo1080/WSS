package Items.Trader;

import java.util.Random;

public class StupidTrader extends Trader {

    private Random rand = new Random();

    public StupidTrader(String name, boolean isRepeating) {
        super(name, isRepeating);
    }

    public boolean randomDecision() {
        
        return rand.nextBoolean();
    }
}