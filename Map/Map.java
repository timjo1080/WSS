package Map;

import java.util.Random;

public class Map {
    
    //vars
    private int width;
    private int height;
    private String difficulty;
    private Square[][] map;

    // Constructor
    public Map(int width, int height, String difficulty) {
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
        this.map = new Square[height][width]; // rows x cols
    }

    //methods
    public void displayMap() {
        // TODO
        if (map == null) {
            System.out.println("Map not initialized.");
            return;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] != null) {
                    System.out.print(map[i][j].getTerrain().getTerrainType().charAt(0) + " ");
                } else {
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
    }

    public void createMap() {
        // TODO - include different difficulties and terrain types
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Random rand = new Random();
                int randterrain = rand.nextInt(5); // 0, 1, 2, 3, or 4
                switch (randterrain) {
                    case 0:
                        map[i][j] = new Square(new Terrain("Plains", 1, 1, 1), j, i);
                        break;
                    case 1:
                        map[i][j] = new Square(new Terrain("Hills", 2, 2, 2), j, i);
                        break;
                    case 2:
                        map[i][j] = new Square(new Terrain("Mountains", 3, 3, 3), j, i);
                        break;
                    case 3:
                        map[i][j] = new Square(new Terrain("Forest", 2, 1, 2), j, i);
                        break;
                    case 4:
                        map[i][j] = new Square(new Terrain("Swamp", 3, 3, 1), j, i);
                        break;
                }
            }
        }
    }

    public boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Square getSquareAt(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            return null;
        }
        return map[y][x];
    }
}
