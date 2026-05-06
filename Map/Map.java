package Map;

import Items.FoodBonus;
import Items.GoldBonus;
import Items.WaterBonus;
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
        Random rand = new Random();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int randTerrain;

                if (difficulty.equalsIgnoreCase("Easy")) {
                    randTerrain = rand.nextInt(10);

                    if (randTerrain < 4) {
                        map[i][j] = new Square(new Terrain("Plains", 1, 1, 1), j, i);
                    } else if (randTerrain < 7) {
                        map[i][j] = new Square(new Terrain("Forest", 2, 1, 2), j, i);
                    } else if (randTerrain < 9) {
                        map[i][j] = new Square(new Terrain("Hills", 2, 2, 2), j, i);
                    } else {
                        map[i][j] = new Square(new Terrain("Mountains", 3, 3, 3), j, i);
                    }

                } else if (difficulty.equalsIgnoreCase("Hard")) {
                    randTerrain = rand.nextInt(10);

                    if (randTerrain < 2) {
                        map[i][j] = new Square(new Terrain("Plains", 1, 1, 1), j, i);
                    } else if (randTerrain < 4) {
                        map[i][j] = new Square(new Terrain("Forest", 2, 1, 2), j, i);
                    } else if (randTerrain < 7) {
                        map[i][j] = new Square(new Terrain("Hills", 2, 2, 2), j, i);
                    } else if (randTerrain < 9) {
                        map[i][j] = new Square(new Terrain("Mountains", 3, 3, 3), j, i);
                    } else {
                        map[i][j] = new Square(new Terrain("Swamp", 3, 3, 1), j, i);
                    }

                } else if (difficulty.equalsIgnoreCase("Medium")) {
                    randTerrain = rand.nextInt(5);

                    switch (randTerrain) {
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

                int itemChance = rand.nextInt(100);

                if (itemChance < 10) {
                    map[i][j].addItem(new Items.FoodBonus("Food Pack", false, 10));
                } else if (itemChance < 20) {
                    map[i][j].addItem(new Items.GoldBonus("Gold Pack", false, 10));
                } else if (itemChance < 30) {
                    map[i][j].addItem(new Items.WaterBonus("Water Pack", false, 10));
                }
            }
        }
    }

    public void displayItemsOnMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Square square = map[y][x];

                if (!square.getItems().isEmpty()) {
                    System.out.println("Square (" + x + ", " + y + ") has:");

                    for (Items.Item item : square.getItems()) {
                        System.out.println("- " + item.getName());
                    }
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
