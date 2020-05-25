package model.character;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cannon extends MovableCharacter{

    private static final Color COLOR = Color.WHITE;
    private static final int LAYOUT_Y = 330;
    private static final int LAYOUT_X = 250;
    private static final int HEIGHT=5;
    private static final int WIDTH=15;

    private Cannon() {
        super(LAYOUT_X, LAYOUT_Y);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                add(new Rectangle(PIXEL, PIXEL, COLOR), j, i);
            }
        }
    }

    public static Cannon createCannon(){
        return new Cannon();
    }

}
