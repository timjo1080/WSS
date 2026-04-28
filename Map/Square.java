package Map;
import Items.Item;
import java.util.ArrayList;

public class Square {
    private Terrain terrain;
    private ArrayList<Item> item;
    private int positionX;
    private int positionY;

    public Square(Terrain terrain, int positionX, int positionY) {
        this.terrain = terrain;
        this.positionX = positionX;
        this.positionY = positionY;
        this.item = new ArrayList<>();
    }
}