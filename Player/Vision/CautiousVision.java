package Player.Vision;
public class CautiousVision extends Vision {

    public CautiousVision() {
        // Wide vertical vision: long north/south, short east/west.
        super(4);
    }

    @Override
    protected boolean isVisibleOffset(int dx, int dy, int radius) {
        return Math.abs(dx) <= 1 && Math.abs(dy) <= radius;
    }
}
