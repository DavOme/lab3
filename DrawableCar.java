import java.awt.image.BufferedImage;

public class DrawableCar {
    public Vehicle vehicle;
    public BufferedImage image;

    public DrawableCar(Vehicle vehicle, BufferedImage image) {
        this.vehicle = vehicle;
        this.image = image;
    }
}