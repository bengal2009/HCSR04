import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Created by Lin on 2015/8/12.
 */
public class SR04_3 {
    private final static float SOUND_SPEED = 340.29f;  // speed of sound in m/s

    private final static int TRIG_DURATION_IN_MICROS = 10; // trigger duration of 10 micro s
    private final static int WAIT_DURATION_IN_MILLIS = 60; // wait 60 milli s

    private final static int TIMEOUT = 2100;
    private static  long end=0,start=0,diff=0;
    static Boolean  isLowFirst=true;
    public static void main(String[] args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput sensor_trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,
                "Sensor Trigger", PinState.LOW);

        GpioPinDigitalInput sensor_result = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00,
                "Sensor Result");

        sensor_result.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                if (event.getState().isLow()) {
                    start = System.nanoTime();

                } else {
//                    end = System.nanoTime();
                    if (start > 0) {
                        diff = System.nanoTime() - start;
                        System.out.println("Duration:" + (long)Math.ceil( (diff ) / 1000.0 ));
                        Long Lenth= ((long)Math.ceil( (diff ) / 1000.0));

                        System.out.println("Length:" + Lenth+1.5 );
                        start = 0;
                    }

                }
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            }

        });

        System.out.println("Waiting....");
        Thread.sleep(1000);
       /* sensor_trigger.high();
        Thread.sleep(0, TRIG_DURATION_IN_MICROS * 1000);
        sensor_trigger.low();*/
        /*while (sensor_result.isLow()) {}
        start=com.pi4j.wiringpi.Gpio.micros();
        while (sensor_result.isHigh()) {}
        end=com.pi4j.wiringpi.Gpio.micros();
        System.out.println("Start:" + start);
        System.out.println("End:"+end);*/
        while(true)
        {
            sensor_trigger.high();
            Thread.sleep(0, TRIG_DURATION_IN_MICROS * 1000);
            sensor_trigger.low();
            Thread.sleep(2000);

        }
    }
}
