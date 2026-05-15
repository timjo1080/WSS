package Player.Vision;
public class FocusedVision extends Vision {

    public FocusedVision() {
        // Narrow horizontal vision: long east/west, short north/south.
        super(4);
    }

    @Override
    protected boolean isVisibleOffset(int dx, int dy, int radius) {
        return Math.abs(dx) <= radius && Math.abs(dy) <= 1;
    }
}
