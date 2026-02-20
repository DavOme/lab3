import java.awt.*;
import java.util.LinkedList;

public abstract class Transportable extends Vehicle {
    public Transportable(double x, double y, int direction, int nrDoors, double enginePower, double currentSpeed, Color color, String modelName, Bed bed) {
        super(x, y, direction, nrDoors, enginePower, currentSpeed, color, modelName, bed);
    }
}
