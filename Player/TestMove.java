package Player;

import Player.Player;
import Player.Brain;
import Player.Vision.Vision;


//use to compile
//javac Items/*.java Items/Trader/*.java Player/*.java Player/Vision/*.java
//use to run
//java Player.TestMove

public class TestMove {
    public static void main(String[] args){
        Vision vision = null;
        Brain brain = null;

        Player player = new Player(
            "Test Player", 100, 100, 100, 100, 100, 100, 100, vision, brain, 5, 0, 0);

        player.move("right", 10, 10);
        player.move("down", 10, 10);
        player.move("left", 10, 10);
        player.move("right", 10, 10);

        player.displayStatus();

    }    
}
