package model.character;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Bunker extends Character {

    private static final Color COLOR = Color.GREEN;
    private static final int LAYOUT_Y = 250;
    private static final int GAP = 150;
    private static final int FIX_X = 50;
    private static final int NUM_BUNK = 4;
    private static final int DIMENSION = 20;


    private Bunker(double layoutX, double layoutY) {
        super(layoutX, layoutY);
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                add(new Rectangle(PIXEL, PIXEL, COLOR), j, i);
            }
        }
    }

    public static ArrayList<Bunker> createBunkers() {
        ArrayList<Bunker> bunkers = new ArrayList<>();
        for (int i = 0; i < NUM_BUNK; i++) {
            bunkers.add(new Bunker(i * GAP + FIX_X, LAYOUT_Y));
        }
        return bunkers;
    }


}
