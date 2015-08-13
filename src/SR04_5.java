import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Lin on 2015/8/11.
 */
public class SR04_5 {


    public static void main(String[] args) throws InterruptedException {
//        final GpioController gpio = GpioFactory.getInstance();

        // keep program running until user aborts (CTRL-C)
        for (; ; ) {
            System.out.println(getSR04());
            Thread.sleep(2000);
        }
    }
    public  static String  getSR04()
    {
        String sOut = "";
        try {
            Runtime run = Runtime.getRuntime();
            Process proc= run.exec("sudo /home/pi/prog/javatest/out/production/HCSR04/ultra");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            // read the output from the command
            String s = "";

            while ((s = stdInput.readLine()) != null) {
                sOut=sOut+s;
            }
//            System.out.println(sOut.trim());
       /*     if((sOut.contains("RH")))
            {
//                System.out.println("OUT:"+sOut);
                sOut=sOut.substring(0,7)+" "+sOut.substring(8,16);
                OldTemp=sOut;
//                System.out.println(sOut+"OldTemp:"+OldTemp);
                return  sOut;
            }
            else
                sOut=OldTemp;

//                System.out.println("DHT11 Error");

            // read any errors from the attempted comman
//            Thread.sleep(100000);*/
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  sOut.trim();
    }

}
