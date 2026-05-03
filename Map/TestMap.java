package Map;

public class TestMap {
	public static void main(String[] args) {
		Map map = new Map(5, 5, "Test Map");
		map.createMap();
		map.displayMap();

		Square square = map.getSquareAt(2, 3);
		if (square != null) {
			System.out.println("Square at (2, 3) has terrain: " + square.getTerrain().getTerrainType());
		} else {
			System.out.println("Square at (2, 3) is out of bounds.");
		}
	}
}
