package Items;

import Player.Player;
import Player.Brain.Brain;
import Player.Vision.Vision;

//use to compile
//javac Items/*.java Items/Trader/*.java Player/*.java Player/Vision/*.java
//use to run
//java Items.TestBonus
public class TestBonus {
    public static void main(String[] args){

        Vision vision = null;
        Brain brain = null;

        Player player = new Player(
        "TestPlayer",
        100, 100, 100,
        50, 50, 20, 10,
        vision, brain,
        10, 0 , 0
    );

        FoodBonus foodBonus = new FoodBonus( "Food Pack", false, 15);
        GoldBonus goldBonus = new GoldBonus("Gold Pack", false, 10);
        WaterBonus waterBonus = new WaterBonus("Water Pack", false, 20);

        foodBonus.addFood(player);
        goldBonus.addGold(player);
        waterBonus.addWater(player);

    }    
}
