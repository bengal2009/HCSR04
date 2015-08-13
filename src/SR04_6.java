import com.pi4j.io.gpio.*;

/**
 * Created by Lin on 2015/8/13.
 */
public class SR04_6 {
    public static void main(String[] args) {
            // Setup GPIO Pins
            GpioController gpio = GpioFactory.getInstance();
            //range finder pins
            GpioPinDigitalOutput rangefindertrigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Range Finder Trigger", PinState.LOW);
            GpioPinDigitalInput rangefinderresult = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, "Range Pulse Result");
                // Create the range finder
            RangeFinder rangefinder = new RangeFinder(rangefindertrigger,rangefinderresult);
            do {   // Get the range
             double distance=rangefinder.getRange();
                System.out.println("RangeFinder result ="+distance +"mm");   }
            while (false!=true);            }
    }
