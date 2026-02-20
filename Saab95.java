import java.awt.*;

public class Saab95 extends Transportable {

    private boolean turboOn;

    public Saab95(){
        super(0.0,0.0,3, 2, 125, 0, Color.red, "Saab95", new Bed());
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return super.getEnginePower() * 0.01 * turbo;
    }

}