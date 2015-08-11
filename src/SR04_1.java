import com.pi4j.io.gpio.*;

/**
 * Created by Lin on 2015/8/11.
 */
public class SR04_1 {


    public static void main(String[] args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput sensor_trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00,
                "Sensor Trigger", PinState.LOW);

        GpioPinDigitalInput sensor_result = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,
                "Sensor Result", PinPullResistance.PULL_DOWN);

// Create the range sensor
        RangeSensor rangesensor = new RangeSensor(sensor_trigger, sensor_result);

        do {
            // Get the range
            double distance = rangesensor.getRange();

            System.out.println("RangeSensorresult =" + distance + "cm");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (true);

    }
}
