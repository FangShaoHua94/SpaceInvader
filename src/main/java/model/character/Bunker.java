package model.character;

import javafx.scene.paint.Color;
import model.Tile;

import java.util.ArrayList;

public class Bunker extends Character {

    private static final Color COLOR = Color.GREEN;
    private static final int DIMENSION = 20;

    private Bunker(ArrayList<Tile> bunker) {
        super(bunker);
    }

    public static Bunker spawnBunker(int row, int col) {
        return new Bunker(spawn(row, col, DIMENSION, COLOR));
    }

    public static boolean belongTo(Tile tile) {
        return tile.getColor().equals(COLOR);
    }
}
