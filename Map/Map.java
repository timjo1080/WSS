package Map;
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
    }

    public void createMap() {
        // TODO
    }
}
