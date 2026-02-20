import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in an appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    static ArrayList<DrawableCar> cars = new ArrayList<>();

    public CarController() {
        initCars();
        frame = new CarView("CarSim 1.0", this, cars);
        timer.start();
    }



    //methods:

    public static void main(String[] args) {
        new CarController();
    }

    private void initCars() {
        try {
            BufferedImage volvoImg = ImageIO.read(getClass().getResourceAsStream("pics/Volvo240.jpg"));
            BufferedImage saabImg = ImageIO.read(getClass().getResourceAsStream("pics/Saab95.jpg"));
            BufferedImage scaniaImg = ImageIO.read(getClass().getResourceAsStream("pics/Scania.jpg"));

            Volvo240 volvo = new Volvo240();

            Saab95 saab = new Saab95();
            saab.setY(100);

            Scania scania = new Scania();
            scania.setY(200);

            cars.add(new DrawableCar(volvo, volvoImg));
            cars.add(new DrawableCar(saab, saabImg));
            cars.add(new DrawableCar(scania, scaniaImg));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            Iterator<DrawableCar> it = cars.iterator();

            while (it.hasNext()) {
                DrawableCar dc = it.next();

                dc.vehicle.move();

                if (collide(dc)) {
                    handleWallCollision(dc);
                }

                if (enterWorkshop(dc)) {
                    if (dc.vehicle instanceof Volvo240) {
                        dc.vehicle.stopEngine();
                        it.remove();
                        continue;
                    }
                }
            }

            frame.repaint();
        }
    }

    private void handleWallCollision(DrawableCar car) {
        car.vehicle.stopEngine();

        car.vehicle.turnRight();
        car.vehicle.turnRight();

        clampPosition(car);

        car.vehicle.startEngine();
    }

    private void clampPosition(DrawableCar car) {
        int width = frame.drawPanel.getWidth();
        int height = frame.drawPanel.getHeight();

        double x = Math.max(0, Math.min(car.vehicle.getX(), width - car.image.getWidth()));
        double y = Math.max(0, Math.min(car.vehicle.getY(), height - car.image.getHeight()));

        car.vehicle.setX(x);
        car.vehicle.setY(y);
    }

    private boolean collide (DrawableCar dc) {
        int width = frame.drawPanel.getWidth();
        int height = frame.drawPanel.getHeight();

        return  dc.vehicle.getX() < 0 ||
                dc.vehicle.getX() > width - dc.image.getWidth() ||
                dc.vehicle.getY() < 0 ||
                dc.vehicle.getY() > height - dc.image.getHeight();

    }

    boolean enterWorkshop(DrawableCar dc) {
        Vehicle car = dc.vehicle;

        int wx = frame.drawPanel.workshopPoint.x;
        int wy = frame.drawPanel.workshopPoint.y;

        int ww = frame.drawPanel.workshopImage.getWidth();
        int wh = frame.drawPanel.workshopImage.getHeight();

        int cw = dc.image.getWidth();
        int ch = dc.image.getHeight();

        return  car.getX() < wx + ww &&
                car.getX() + cw > wx &&
                car.getY() < wy + wh &&
                car.getY() + ch > wy;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (DrawableCar car : cars) {
            car.vehicle.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (DrawableCar car : cars) {
            car.vehicle.brake(brake);
        }
    }

    void stop () {
        for (DrawableCar car : cars) {
            car.vehicle.stopEngine();
        }
    }

    void start () {
        for (DrawableCar car : cars) {
            car.vehicle.startEngine();
        }
    }

    void turboOn () {
        for (DrawableCar car : cars) {
            if (car.vehicle instanceof Saab95) {
                ((Saab95) car.vehicle).setTurboOn();
            }
        }
    }

    void turboOff () {
        for (DrawableCar car : cars) {
            if (car.vehicle instanceof Saab95) {
                ((Saab95) car.vehicle).setTurboOff();
            }
        }
    }

    void liftBed () {
        for (DrawableCar car : cars) {
            if (car.vehicle instanceof Scania) {
                ((Scania) car.vehicle).incrementDegree();
            }
        }
    }

    void lowerBed () {
        for (DrawableCar car : cars) {
            if (car.vehicle instanceof Scania) {
                ((Scania) car.vehicle).decrementDegree();
            }
        }
    }



}