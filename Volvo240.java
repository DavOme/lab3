import java.awt.*;

public class Volvo240 extends Transportable {

    private final static double trimFactor = 1.25;

    public Volvo240(){
        super(0.0,0.0,0,4, 100, 0, Color.black, "Volvo240", new Bed());
    }

    @Override
    public double speedFactor(){
        return super.getEnginePower() * 0.01 * trimFactor;
    }
}
