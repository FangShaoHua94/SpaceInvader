package model.character.projectile;

import javafx.scene.paint.Color;
import model.Tile;
import model.character.Speed;

import java.util.ArrayList;

public class AlienProjectile extends Projectile {

    protected static final Color COLOR = Color.RED;
    private static final int SPEED = 2;
    private static final int DIMENSION = 2;

    private AlienProjectile(ArrayList<Tile> projectile, Speed speed) {
        super(projectile, speed);
    }

    public static Projectile spawnAlienProjectile(int row, int col) {
        return new AlienProjectile(spawn(row, col, DIMENSION, COLOR), new Speed(SPEED));
    }

    public static boolean belongTo(Tile tile) {
        return tile.getColor().equals(COLOR);
    }

    @Override
    public void advance() {
        moveDown();
    }

    @Override
    public int nextRow() {
        return getCharacter().get(getCharacter().size() - 1).getRow() + SPEED;
    }

    @Override
    public Projectile duplicate() {
        ArrayList<Tile> duplicate = new ArrayList<>();
        getCharacter().forEach(tile -> duplicate.add(new Tile(tile.getRow(), tile.getCol(), tile.getColor())));
        return new AlienProjectile(duplicate, new Speed(SPEED));
    }

}
