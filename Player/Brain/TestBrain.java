package Player.Brain;
import Player.Player;
import Player.Vision.*;
import Map.Map;
import Map.Terrain;

public class TestBrain {
	public static void main(String[] args) {
		Map map = new Map(10, 10, "easy");
		map.createMap();
		System.out.println("");

		Vision vision = new FocusedVision();
		Brain brain = new SpeedyBrain(vision, map);

		Player player = new Player(
			"Test Player", 100, 100, 100, 100, 100, 100, 100, vision, brain, 5, 0, 0);

		map.displayMap(player.getPositionX(), player.getPositionY());

		player.displayStatus();
		System.out.println("");

		for(int i = 0; i < 6; i ++){
			brain.makeMove(player);
			map.displayMap(player.getPositionX(), player.getPositionY());

			System.out.println("");

			player.displayStatus();
		}

	}
}
