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

    public void applyItems(Player.Player player) {
        // starts backwards
            for (int i = items.size() - 1; i >= 0; i--) {
                Item item = items.get(i);
                
                item.applyEffect(player, item, this);
                
                if (!item.isRepeating()) {
                    items.remove(i); 
            }
        }
    }

    public static int getGoldValue() {
        return 10;
    }

     public static int getWaterValue() {
        return 10;
    }

    public static int getFoodValue() {
        return 10;
    }
}
