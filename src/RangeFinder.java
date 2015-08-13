/**
 * Created by Lin on 2015/8/12.
 */

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class RangeFinder {
    double result = 0;
    GpioPinDigitalOutput firepulse;
    GpioPinDigitalInput result_pin;

    RangeFinder(GpioPinDigitalOutput trigger, GpioPinDigitalInput result_pin) {
        this.firepulse = trigger;
        this.result_pin = result_pin;
    }
    public double getRange() {
        System.out.println("Range Finder Triggered");
        try {
            // fire the trigger pulse
            firepulse.high();
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Exception triggering range finder");
        }
        firepulse.low();
        // wait for the result
        double startTime = System.currentTimeMillis();
        double stopTime = 0;
        do {
            stopTime = System.currentTimeMillis();
            if ((System.currentTimeMillis() - startTime) >= 40) {
                break;
            }   } while (result_pin.getState() != PinState.HIGH);
             // calculate the range. If the loop stopped after 38 ms set the result
             // to -1 to show it timed out.
             if ((stopTime - startTime) <= 38) {
                 result = (stopTime - startTime) * 165.7;   }
             else {    System.out.println("Timed out");
                 result = -1;
             }
        return result;
    }
    }

