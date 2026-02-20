import java.awt.*;
import java.util.LinkedList;

public abstract class Transporter extends Vehicle {
    public LinkedList<Transportable> load;

    public Transporter(double x, double y, int direction, int nrDoors, double enginePower, double currentSpeed, Color color, String modelName, Bed bed, LinkedList<Transportable> load) {
        super(x, y, direction, nrDoors, enginePower, currentSpeed, color, modelName, bed);
        this.load = load;
    }

    @Override
    public void move() {
        if (direction == 3) {
            x += currentSpeed;
        }
        else if (direction == 9) {
            x -= currentSpeed;
        }
        else if (direction == 6) {
            y -= currentSpeed;
        }
        else y += currentSpeed;
        for (Vehicle v : this.load) {
            v.setX(this.getX());
            v.setY(this.getY());
        }
    }

    public void loadBed(Transportable t) {
        if (!getBedState()) { throw new IllegalStateException("Bed must be open to load vehicles"); }
        else if (this.distanceBetweenX(t) <= 5 && this.distanceBetweenY(t) <= 5) {
            load.addLast(t);
            t.setX(this.getX());
            t.setY(this.getY());
        }
    }

    public void unloadBed() {
        if (!getBedState()) {
            throw new IllegalStateException("Bed must be open to unload vehicles");
        }
        /*
        else if (load.isEmpty()) {
            throw new IllegalStateException("Bed is currently loadEmpty");
        }
        "removeLast tar redan hand om detta"
         */
        else {
            lastVehicle().setX(this.getX() + 5);
            lastVehicle().setDirection(this.getDirection());
            load.removeLast();
        }
    }

    public int getLoadSize() {
        return load.size();
    }

    public Vehicle lastVehicle() {
        return load.getLast();
    }

    public boolean loadEmpty() {
        return load.isEmpty();
    }
}

