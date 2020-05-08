package logic;

import java.util.concurrent.TimeUnit;

public class FrameDelay {

    public static void delay(long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException ie) {
            //suppress
        }
    }

}
