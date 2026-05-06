package Player;
import Items.Trader.Offer;
import Player.Vision.Vision;

public class Player {
    private String name;
    private int maxStrength;
    private int maxWater;
    private int maxFood;
    private int currentFood;
    private int currentStrength;
    private int currentWater;
    private int currentGold;
    private Vision vision;
    private Brain brain;
    private int movementPts;
    private int positionX;
    private int positionY;

    public Player(String name, int maxStrength, int maxWater, int maxFood,
                  int currentStrength, int currentWater, int currentFood, int currentGold,
                  Vision vision, Brain brain,
                  int movementPts, int positionX, int positionY) {
        this.name = name;
        this.maxStrength = maxStrength;
        this.maxWater = maxWater;
        this.maxFood = maxFood;
        this.currentStrength = currentStrength;
        this.currentWater = currentWater;
        this.currentGold = currentGold;
        this.currentFood = currentFood;
        this.vision = vision;
        this.brain = brain;
        this.movementPts = movementPts;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void move() {
        // TODO
    }

    public void rest() {
        // TODO
    }

    public void proposeTrade(Offer offer) {
        // TODO
    }

    public void acceptTrade(Offer offer) {
        // TODO
    }

    public void rejectTrade() {
        // TODO
    }
    
    public void addFood(int amount) {
        currentFood += amount;
        if (currentFood > maxFood) {
            currentFood = maxFood;
        }
        System.out.println("Food is now: " + currentFood);
    }

    public void addGold(int amount) {
        currentGold += amount;
        if (currentGold < 0) {
            currentGold = 0;
        }
        System.out.println("Gold is now: " + currentGold);
    }

    public void addWater(int amount) {
        currentWater += amount;
        if (currentWater > maxWater) {
            currentWater = maxWater;
        }
        System.out.println("Water is now: " + currentWater);
    }

    public Brain getBrain() {
        // TODO Auto-generated method stub
        return brain;
    }
}