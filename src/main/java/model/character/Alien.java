package model.character;

import javafx.scene.paint.Color;
import model.Tile;
import model.character.projectile.Projectile;

import java.util.ArrayList;

import static model.character.projectile.AlienProjectile.spawnAlienProjectile;

public class Alien extends CombatCharacter {

    private static final Color COLOR = Color.PURPLE;
    private static final int SPEED = 1;
    private static final int DIMENSION = 7;

    private Alien(ArrayList<Tile> cannon, Speed speed) {
        super(cannon, speed);
    }

    public static Alien spawnAlien(int row, int col) {
        return new Alien(spawn(row, col, DIMENSION, COLOR), new Speed(SPEED));
    }

    @Override
    public Projectile fire() {
        if (!hasFired()) {
            Projectile projectile = spawnAlienProjectile(
                    getCharacter().get(DIMENSION * DIMENSION - DIMENSION / 2 + 1).getRow() + 1,
                    getCharacter().get(DIMENSION * DIMENSION - DIMENSION / 2 + 1).getCol());
            setProjectile(projectile);
            return projectile;
        }
        return null;
    }

    @Override
    public int nextLeftCol() {
        return getCharacter().get(0).getCol() - SPEED;
    }

    @Override
    public int nextRightCol() {
        return getCharacter().get(6).getCol() + SPEED;
    }

    @Override
    public void moveDown() {
        for (int i = 0; i < 3; i++) {
            super.moveDown();
        }
    }

}
