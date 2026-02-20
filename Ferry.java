import java.awt.*;
import java.util.LinkedList;

public class Ferry extends Transporter{
    public Ferry() {
        super(0.0,0.0,3,2, 200, 0, Color.white, "Stena Line", new Bed(0, 0, true, false, false), new LinkedList<>());
    }

    @Override
    public void loadBed(Transportable t) {
        if (!getBedState()) { throw new IllegalStateException("Bed must be open to load vehicles"); }
        else if (this.distanceBetweenX(t) <= 5 && this.distanceBetweenY(t) <= 5) {
            load.addFirst(t);
            t.setX(this.getX());
            t.setY(this.getY());
        }
    }
}
