package Player.Vision;

import Items.Item;
import Items.Trader.Trader;
import Map.Map;
import Map.Square;
import java.util.ArrayList;

public class Vision {
    // Visible squares for the current turn.
    private final ArrayList<Square> visibility;
    // Visibility radius for this vision type.
    private final int radius;
    // Player square used as the origin for distance and path calculations.
    private Square currentSquare;

    public Vision() {
        this(2);
    }

    public Vision(int radius) {
        this.visibility = new ArrayList<>();
        this.radius = Math.max(0, radius);
        this.currentSquare = null;
    }

    public int getRadius() {
        return radius;
    }

    // Uses this vision type's configured radius.
    public void updateVisibility(Map map, int currentX, int currentY) {
        updateVisibility(map, currentX, currentY, getRadius());
    }

    // Rebuilds visibility using a box radius centered on (currentX, currentY).
    // Subclasses can override isVisibleOffset(...) to use non-box shapes.
    public void updateVisibility(Map map, int currentX, int currentY, int radius) {
        visibility.clear();
        currentSquare = null;

        if (map == null || radius < 0) {
            return;
        }

        Square origin = map.getSquareAt(currentX, currentY);
        if (origin == null) {
            return;
        }

        currentSquare = origin;

        int minX = currentX - radius;
        int maxX = currentX + radius;
        int minY = currentY - radius;
        int maxY = currentY + radius;

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                int dx = x - currentX;
                int dy = y - currentY;

                if (!isVisibleOffset(dx, dy, radius)) {
                    continue;
                }

                if (!map.isValidCoordinate(x, y)) {
                    continue;
                }

                Square square = map.getSquareAt(x, y);
                if (square != null) {
                    visibility.add(square);
                }
            }
        }
    }

    // Default shape: box visibility.
    protected boolean isVisibleOffset(int dx, int dy, int radius) {
        return Math.abs(dx) <= radius && Math.abs(dy) <= radius;
    }

    public Path closestFood() {
        return nthClosestItemPath("Food Pack", 1);
    }

    public Path closestWater() {
        return nthClosestItemPath("Water Pack", 1);
    }

    public Path closestGold() {
        return nthClosestItemPath("Gold Pack", 1);
    }

    public Path closestTrader() {
        return nthClosestTraderPath(1);
    }

    public Path secondClosestFood() {
        return nthClosestItemPath("Food Pack", 2);
    }

    public Path secondClosestWater() {
        return nthClosestItemPath("Water Pack", 2);
    }

    public Path secondClosestGold() {
        return nthClosestItemPath("Gold Pack", 2);
    }

    public Path secondClosestTrader() {
        return nthClosestTraderPath(2);
    }

    public ArrayList<Square> getVisibility() {
        return new ArrayList<>(visibility);
    }

    public void setVisibility(ArrayList<Square> visibility) {
        this.visibility.clear();
        if (visibility == null) {
            return;
        }
        this.visibility.addAll(visibility);
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
    }

    private Path nthClosestItemPath(String itemName, int n) {
        // Filter visible squares to those containing the requested item, then rank by distance.
        if (visibility.isEmpty() || currentSquare == null || n <= 0) {
            return null;
        }

        ArrayList<Square> matches = new ArrayList<>();

        for (Square square : visibility) {
            if (square == null || isSameSquare(square, currentSquare) || !containsItem(square, itemName)) {
                continue;
            }
            insertByDistance(currentSquare, matches, square);
        }

        if (matches.size() < n) {
            return null;
        }

        return buildPath(currentSquare, matches.get(n - 1));
    }

    private Path nthClosestTraderPath(int n) {
        // Same ranking flow as items, but checks for Trader instances.
        if (visibility.isEmpty() || currentSquare == null || n <= 0) {
            return null;
        }

        ArrayList<Square> matches = new ArrayList<>();

        for (Square square : visibility) {
            if (square == null || isSameSquare(square, currentSquare) || !containsTrader(square)) {
                continue;
            }
            insertByDistance(currentSquare, matches, square);
        }

        if (matches.size() < n) {
            return null;
        }

        return buildPath(currentSquare, matches.get(n - 1));
    }

    private void insertByDistance(Square origin, ArrayList<Square> ranked, Square candidate) {
        // Keep list sorted by nearest-first so nth lookup is O(1) after insertion.
        int candidateDistance = manhattanDistance(origin, candidate);

        for (int i = 0; i < ranked.size(); i++) {
            int existingDistance = manhattanDistance(origin, ranked.get(i));
            if (candidateDistance < existingDistance) {
                ranked.add(i, candidate);
                return;
            }
        }

        ranked.add(candidate);
    }

    private boolean containsItem(Square square, String itemName) {
        for (Item item : square.getItems()) {
            if (item != null && itemName.equalsIgnoreCase(item.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean containsTrader(Square square) {
        for (Item item : square.getItems()) {
            if (item instanceof Trader) {
                return true;
            }
        }
        return false;
    }

    private int manhattanDistance(Square from, Square to) {
        return Math.abs(from.getPositionX() - to.getPositionX()) +
               Math.abs(from.getPositionY() - to.getPositionY());
    }

    private boolean isSameSquare(Square a, Square b) {
        if (a == null || b == null) {
            return false;
        }
        return a.getPositionX() == b.getPositionX() && a.getPositionY() == b.getPositionY();
    }

    private Path buildPath(Square from, Square to) {
        if (from == null || to == null) {
            return null;
        }

        int dx = to.getPositionX() - from.getPositionX();
        int dy = to.getPositionY() - from.getPositionY();
        int steps = Math.abs(dx) + Math.abs(dy);

        // Simplified model: each step costs 1 movement, 1 water, and 1 food.
        Path path = new Path(steps, steps, steps);

        // Route is deterministic: horizontal moves first, then vertical moves.
        String horizontal = dx >= 0 ? "right" : "left";
        for (int i = 0; i < Math.abs(dx); i++) {
            path.totalPath().add(horizontal);
        }

        String vertical = dy >= 0 ? "up" : "down";
        for (int i = 0; i < Math.abs(dy); i++) {
            path.totalPath().add(vertical);
        }

        return path;
    }
}
