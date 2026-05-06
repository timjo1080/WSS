package Player.Brain;
import Map.*;
import Player.Player;
import Player.Vision.Vision;

public class TestBrain {
	public static void main(String[] args) {
		Map map = new Map(5, 5, "Test Map");
		map.createMap();
		map.displayMap();
		System.out.println("");

		Vision vision = new Vision();
		Brain brain = new Brain(vision, map);

		Player player = new Player(
			"Test Player", 100, 100, 100, 100, 100, 100, 100, vision, brain, 5, 0, 0);

		player.displayStatus();
		System.out.println("");


		brain.makeMove(player);
		System.out.println("");

		player.displayStatus();
	}
}
