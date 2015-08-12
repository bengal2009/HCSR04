import com.pi4j.io.gpio.*;

/**
 * Created by Lin on 2015/8/12.
 */
public class SR04_4 {
    public static void main(String[] args) throws InterruptedException {
        com.pi4j.wiringpi.Gpio.wiringPiSetup();

        final GpioController gpio = GpioFactory.getInstance();

//range sensor pins
        GpioPinDigitalOutput sensor_trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,
                "Sensor Trigger", PinState.LOW);

        GpioPinDigitalInput sensor_result = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00,
                "Sensor Result",PinPullResistance.PULL_UP);
        double distance;
// Create the range sensor
        RangeSensor rangesensor = new RangeSensor(sensor_trigger, sensor_result);

        do {
            // Get the range
            distance = rangesensor.getRange();

            System.out.println("RangeSensorresult =" + distance + "cm");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
