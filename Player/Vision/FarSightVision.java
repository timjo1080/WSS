package Player.Vision;
public class FarSightVision extends Vision {

    public FarSightVision() {
        // Cross vision: sees far in four cardinal directions.
        super(6);
    }

    @Override
    protected boolean isVisibleOffset(int dx, int dy, int radius) {
        return (dx == 0 || dy == 0) && Math.max(Math.abs(dx), Math.abs(dy)) <= radius;
    }
}
