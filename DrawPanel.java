import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    private ArrayList<DrawableCar> cars;
    BufferedImage workshopImage;
    Point workshopPoint = new Point(685, 0);

    public DrawPanel(int x, int y, ArrayList<DrawableCar> cars) {
        this.cars = cars;
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        try {
            workshopImage = ImageIO.read(getClass().getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (DrawableCar dc : cars) {
            int x = (int) dc.vehicle.getX();
            int y = (int) dc.vehicle.getY();
            g.drawImage(dc.image, x, y, null);
        }

        g.drawImage(workshopImage, workshopPoint.x, workshopPoint.y, null);
    }
}