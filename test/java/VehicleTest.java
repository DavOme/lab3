import org.junit.jupiter.api.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    Transportable testCar1 = new Saab95();
    Transportable testCar2 = new Volvo240();
    Transportable testCar3 = new Scania();
    Transporter testCar4 = new VolvoFH();
    Transporter testFerry = new Ferry();

    @Test
    void spin() {
        testCar1.turnLeft();
        testCar1.turnRight();
        testCar1.turnLeft();
        testCar1.turnRight();
        testCar1.turnLeft();
        testCar1.turnLeft();
        testCar1.turnLeft();

        assertEquals(3, testCar1.getDirection());
    }

    @Test
    void turnLeft() {
        testCar1.turnLeft();
        testCar1.turnLeft();
        testCar1.turnLeft();

        assertEquals(3, testCar1.getDirection());
    }

    @Test
    void turnRight() {
        testCar1.turnRight();
        testCar1.turnRight();
        testCar1.turnRight();

        assertEquals(9, testCar1.getDirection());
    }

    @Test
    void gas() {
        double oldSpeed = testCar1.getCurrentSpeed();
        testCar1.gas(1);
        assertTrue(testCar1.getCurrentSpeed() > oldSpeed);
    }

    @Test
    void brake() {
        testCar1.gas(1.0);
        double oldSpeed = testCar1.getCurrentSpeed();
        testCar1.brake(0.5);
        assertTrue(testCar1.getCurrentSpeed() < oldSpeed);
    }

    @Test
    void nrDoors() {
        assertEquals(2, testCar1.getNrDoors());
    }

    @Test
    void color() {
        assertEquals(Color.red, testCar1.getColor());

        testCar1.setColor(Color.black);
        assertEquals(Color.black, testCar1.getColor());
    }

    @Test
    void move() {
        testCar1.gas(1); //currentspeed will be 1.25
        testCar1.move();
        assertEquals(1.25, testCar1.getY());
        testCar1.turnRight();
        testCar1.move();
        assertEquals(1.25, testCar1.getX());

    }

    @Test
    void liftBed() {
        testCar3.startEngine();
        testCar3.incrementDegree();
        assertEquals(0, testCar3.bed.getDegree());

        testCar3.stopEngine();
        testCar3.incrementDegree();
        assertEquals(1, testCar3.bed.getDegree());
    }

    @Test
    void lowerBed() {
        testCar3.bed.setDegree(25);
        testCar3.decrementDegree();
        assertEquals(24, testCar3.bed.getDegree());
    }

    @Test
    void setBedDegree() {
        testCar3.bed.setDegree(25);
        assertEquals(25, testCar3.bed.getDegree());
    }

    @Test
    void bedStateTest() {
        testCar4.bed.open();
        assertTrue(testCar4.getBedState());
        testCar4.bed.close();
        assertFalse(testCar4.getBedState());
    }

    @Test
    void loadBedTest() {
        testCar4.bed.open();

        testCar2.setX(20);
        testCar4.loadBed(testCar2);
        assertTrue(testCar4.loadEmpty());

        testCar2.setX(2);
        testCar2.setY(15);
        testCar4.loadBed(testCar2);
        assertTrue(testCar4.loadEmpty());

        testCar2.setY(5);
        testCar4.loadBed(testCar2);
        assertFalse(testCar4.loadEmpty());

        testCar4.bed.close();
        testCar4.gas(1);
        testCar4.move();
        assertEquals(testCar4.getX(), testCar2.getX());
    }

    @Test
    void loadFerry() {
        testFerry.bed.open();

        testCar2.setX(20);
        testFerry.loadBed(testCar2);
        assertTrue(testFerry.loadEmpty());

        testCar2.setX(2);
        testCar2.setY(15);
        testFerry.loadBed(testCar2);
        assertTrue(testFerry.loadEmpty());

        testCar2.setY(5);
        testFerry.loadBed(testCar2);
        assertFalse(testFerry.loadEmpty());

        testFerry.bed.close();
        testFerry.gas(1);
        testFerry.move();
        assertEquals(testFerry.getX(), testCar2.getX());
    }

    @Test
    void unloadBedTest() {
        testCar4.bed.open();

        testCar4.loadBed(testCar2);
        assertFalse(testCar4.loadEmpty());

        testCar4.unloadBed();
        assertTrue(testCar4.loadEmpty());

        testCar4.loadBed(testCar2);
        testCar4.loadBed(testCar1);
        testCar4.loadBed(testCar3);
        assertEquals(3, testCar4.getLoadSize());
        assertSame(testCar4.lastVehicle(), testCar3);

        testCar4.unloadBed();
        assertEquals(2, testCar4.getLoadSize());
        assertNotSame(testCar4.lastVehicle(), testCar3);
        assertEquals(testCar4.getX() + 5, testCar3.getX());
    }

    @Test
    void unloadFerryTest() {
        testFerry.bed.open();

        testFerry.loadBed(testCar2);
        assertFalse(testFerry.loadEmpty());

        testFerry.unloadBed();
        assertTrue(testFerry.loadEmpty());

        testFerry.loadBed(testCar2);
        testFerry.loadBed(testCar1);
        testFerry.loadBed(testCar3);
        assertEquals(3, testFerry.getLoadSize());
        assertSame(testFerry.lastVehicle(), testCar2);

        testFerry.unloadBed();
        assertEquals(2, testFerry.getLoadSize());
        assertNotSame(testFerry.lastVehicle(), testCar2);
        assertEquals(testFerry.getX() + 5, testCar2.getX());
    }
}