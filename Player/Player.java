package Player;
import Items.Trader.Offer;
import Player.Brain.Brain;
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

    public void move(String direction, int mapWidth, int mapHeight) {
        if (movementPts <= 0) {
            System.out.println("No movement points left.");
            return;
        }

        int newX = positionX;
        int newY = positionY;
        
        if (direction.equalsIgnoreCase("up")) {
            newY -= 1;
        }
        else if (direction.equalsIgnoreCase("down")){
            newY += 1;
        }
        else if (direction.equalsIgnoreCase("left")){
            newX -= 1;
        }
        else if (direction.equalsIgnoreCase("right")){
            newX += 1;
        }
        else
        {
            System.out.println("Invalid direction. Use up, down, left, or right.");
            return;
        }

        if (newX < 0 || newX >= mapWidth || newY < 0 || newY >= mapHeight) {
            System.out.println( "You cannot move off the map.");
            return;

        }

        positionX = newX;
        positionY = newY;
        movementPts--;
        currentStrength--;

        if (currentStrength < 0) {
            currentStrength = 0;
        }

        System.out.println(name + " moved " + direction);
        System.out.println("Current position: (" + positionX + ", " + positionY + ")");

    }

    public void rest() {
        currentStrength += 10;

        if (currentStrength > maxStrength) {
            currentStrength = maxStrength;
        }

        currentFood -= 1;
        currentWater -= 1;

        if (currentFood < 0) {
            currentFood = 0;
        }

        if (currentWater < 0) {
            currentWater = 0;
        }

        System.out.println(name + " rested.");
    }

    public void proposeTrade(Offer offer) {
        System.out.println(name + " proposes a trade: ");
        System.out.println(offer);
    }

    public void acceptTrade(Offer offer) {

        if (currentGold < offer.getGoldOffered() || 
            currentWater < offer.getWaterOffered() ||
            currentFood < offer.getFoodOffered()) {
                System.out.println("Not enough resources to complete the trade.");
                return;       
        }
        
        currentGold -= offer.getGoldOffered();
        currentWater -= offer.getWaterOffered();
        currentFood -= offer.getFoodOffered();
        
        currentGold += offer.getGoldRequested();
        currentWater += offer.getWaterRequested();
        currentFood += offer.getFoodRequested();

        if (currentGold < 0) currentGold = 0;
        if (currentWater < 0) currentWater = 0;
        if (currentFood < 0) currentFood = 0;

        if (currentWater > maxWater) currentWater = maxWater;
        if (currentFood > maxFood) currentFood = maxFood;

        System.out.println(name + " accepted the trade.");

    }

    public void rejectTrade() {
        System.out.println(name + " rejected the trade.");
    }

    public String getName() {
        return name;

    }

    public int getCurrentFood() {
        return currentFood;

    }

    public int getCurrentWater() {
        return currentWater;

    }

    public int getCurrentGold() {
        return currentGold;

    }

    public int getCurrentMovementPts() {
        return movementPts;

    }

    public int getCurrentX() {
        return positionX;

    }

    public int getCurrentY() {
        return positionY;

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

    public void displayStatus() {
        System.out.println("Player: " + name);
        System.out.println("Food: " + currentFood + "/" + maxFood);
        System.out.println("Water: " + currentWater + "/" + maxWater);
        System.out.println("Gold: " + currentGold);
        System.out.println("Position: (" + positionX + ", " + positionY + ")");

    }
}