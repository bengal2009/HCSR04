import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Created by Lin on 2015/8/12.
 */
public class SR04_2 {
    private final static float SOUND_SPEED = 340.29f;  // speed of sound in m/s

    private final static int TRIG_DURATION_IN_MICROS = 10; // trigger duration of 10 micro s
    private final static int WAIT_DURATION_IN_MILLIS = 60; // wait 60 milli s

    private final static int TIMEOUT = 2100;
    private static  double end=0,start=0,diff=0;
    final static GpioController gpio = GpioFactory.getInstance();
    final static GpioPinDigitalOutput sensor_trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,
            "Sensor Trigger", PinState.LOW);
    final static GpioPinDigitalInput sensor_result = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00,
            "Sensor Result");
    public static void main(String[] args) throws InterruptedException {



        sensor_result.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                if (event.getState().isLow()) {
                    start = com.pi4j.wiringpi.Gpio.micros();

                } else {
                    end = com.pi4j.wiringpi.Gpio.micros();
                    diff = end - start;
                    System.out.println("Duration:" + diff);
                    System.out.println(diff / 58);
                /*    diff = (System.nanoTime() - start) / 58000;
//                    diff = (long)Math.ceil( ( end - start ) / 1000.0);
                    System.out.println("IsHigh");
                    System.out.println("Start:" + start + "-End:" + end);
                    System.out.println("diff:" + diff);
                    System.out.println("Distance:" + diff* SOUND_SPEED / ( 2 * 10000 ));
*/
                }
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            }

        });

        System.out.println("Waiting....");
        Thread.sleep(2000);
        sensor_trigger.high();
        Thread.sleep(0, TRIG_DURATION_IN_MICROS * 1000);
        sensor_trigger.low();
    }

    }
