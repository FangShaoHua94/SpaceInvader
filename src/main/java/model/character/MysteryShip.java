package model.character;

import javafx.scene.paint.Color;
import model.Score;
import model.Tile;

import java.util.ArrayList;

public class MysteryShip extends MovableCharacter implements LeftRightMotion, HasPoint {

    private static final Color COLOR = Color.BLUE;
    private static final Score POINT = new Score(1000);
    private static final int SPEED = 3;

    private MysteryShip(ArrayList<Tile> mysteryShip, Speed speed) {
        super(mysteryShip, speed);
    }

    public static MysteryShip spawnMysteryShip(int row, int col) {
        ArrayList<Tile> mysteryShip = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                mysteryShip.add(new Tile(row + i, col + j, COLOR));
            }
        }
        return new MysteryShip(mysteryShip, new Speed(SPEED));
    }

    public static boolean belongTo(Tile tile) {
        return tile.getColor().equals(COLOR);
    }

    @Override
    public int nextLeftCol() {
        return getCharacter().get(0).getCol() - SPEED;
    }

    @Override
    public int nextRightCol() {
        return getCharacter().get(getCharacter().size() - 1).getCol() + SPEED;
    }

    @Override
    public Score getPoint() {
        return POINT;
    }
}
