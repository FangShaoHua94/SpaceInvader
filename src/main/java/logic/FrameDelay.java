package logic;

import java.util.concurrent.TimeUnit;

public class FrameDelay {

    private static final long GAME_DELAY = 50;
    private static final long FLICKING_DELAY = 20;
    private static final long CONTROL_DELAY = 50;
    private static final long STARTING_DELAY = 1000;

    private static void delay(long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException ie) {
            //suppress
        }
    }

    public static void gameDelay() {
        delay(GAME_DELAY);
    }

    public static void flickingDelay() {
        delay(FLICKING_DELAY);
    }

    public static void controlDelay() {
        delay(CONTROL_DELAY);
    }

    public static void startingDelay() {
        delay(STARTING_DELAY);
    }

}
