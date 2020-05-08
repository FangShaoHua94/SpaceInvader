package model.character.projectile;

import javafx.scene.paint.Color;
import model.Tile;
import model.character.Speed;

import java.util.ArrayList;

public class CannonProjectile extends Projectile {

    protected static final Color COLOR = Color.YELLOW;
    private static final int SPEED = 3;

    private CannonProjectile(ArrayList<Tile> projectile, Speed speed) {
        super(projectile, speed);
    }

    public static Projectile spawnCannonProjectile(int row, int col) {
        ArrayList<Tile> projectile = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                projectile.add(new Tile(row + i, col + j, COLOR));
            }
        }
        return new CannonProjectile(projectile, new Speed(SPEED));
    }

    @Override
    public void advance() {
        moveUp();
    }

    @Override
    public int nextRow() {
        return getCharacter().get(0).getRow() - SPEED;
    }

    @Override
    public Projectile duplicate() {
        ArrayList<Tile> duplicate = new ArrayList<>();
        getCharacter().forEach(tile -> duplicate.add(new Tile(tile.getRow(), tile.getCol(), tile.getColor())));
        return new CannonProjectile(duplicate, new Speed(SPEED));
    }

    public static boolean belongTo(Tile tile) {
        return tile.getColor().equals(COLOR);
    }

}
