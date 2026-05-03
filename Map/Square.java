package Map;
import Items.Item;
import java.util.ArrayList;

public class Square {
    private Terrain terrain;
    private ArrayList<Item> items;
    private int positionX;
    private int positionY;

    public Square(Terrain terrain, int positionX, int positionY) {
        this.terrain = terrain;
        this.positionX = positionX;
        this.positionY = positionY;
        this.items = new ArrayList<>();
    }
    
    public Terrain getTerrain() {
        return terrain;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }
}