import java.awt.*;

public abstract class Vehicle implements Movable {

    double x;
    double y;
    int direction;
    int nrDoors; // Number of doors on the car
    double enginePower; // Engine power of the car
    double currentSpeed; // The current speed of the car
    Color color; // Color of the car
    String modelName;// The car model name
    Bed bed;

    public Vehicle(double x, double y, int direction, int nrDoors, double enginePower, double currentSpeed, Color color, String modelName, Bed bed) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.currentSpeed = currentSpeed;
        this.color = color;
        this.modelName = modelName;
        this.bed = bed;
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
    }

    @Override
    public void turnLeft() {
        direction = (direction + 9) % 12;
    }

    @Override
    public void turnRight() {
        direction = (direction + 3) % 12;
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    private void incrementSpeed(double amount){
        if (getBedState()) throw new IllegalStateException("Bed must be closed / lifted prior to acceleration");
        else {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        }
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }


    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

    public void incrementDegree() {
        if (currentSpeed == 0) {
            bed.increment();
        }
    }

    public void decrementDegree() {
        if (currentSpeed == 0) {
            bed.decrement();
        }
    }

    public boolean getBedState() {
        return bed.bedOpen; // True means bed is open, False means bed is closed
    }

    public double distanceBetweenX(Vehicle vehicle) {
        return Math.abs(this.x - vehicle.x);
    }

    public double distanceBetweenY(Vehicle vehicle) {
        return Math.abs(this.y - vehicle.y);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int newDirection) {
        this.direction = newDirection;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double newX) {
        this.x = newX;
    }

    public void setY(double newY) {
        this.y = newY;
    }

    public void gas(double amount){
        if (amount < 0.0 || amount > 1.0) throw new IllegalArgumentException("Gas must be between 0, 1");
        else {
            incrementSpeed(amount);
        }
    }

    public void brake(double amount){
        if (amount < 0.0 || amount > 1.0) throw new IllegalArgumentException("Brake must be between 0, 1");
        else {
            decrementSpeed(amount);
        }
    }

    @Override
    public String toString() {
        return "Car [Modelname:" + modelName + "]";
    }
}
