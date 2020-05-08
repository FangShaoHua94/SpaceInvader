package model.character;

import javafx.scene.paint.Color;
import model.Tile;
import model.character.projectile.Projectile;


import java.util.ArrayList;

import static model.character.projectile.CannonProjectile.spawnCannonProjectile;

public class Cannon extends CombatCharacter {

    private static final Color COLOR = Color.ORANGE;
    private static final int SPEED = 5;

    private Cannon(ArrayList<Tile> cannon, Speed speed) {
        super(cannon, speed);
    }

    public static Cannon spawnCannon(int row, int col) {
        ArrayList<Tile> cannon = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                cannon.add(new Tile(row + i, col + j, COLOR));
            }
        }
        return new Cannon(cannon, new Speed(SPEED));
    }

    @Override
    public Projectile fire() {
        if (!hasFired()) {
            Projectile projectile = spawnCannonProjectile(getCharacter().get(4).getRow() - 3,
                    getCharacter().get(4).getCol());
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
        return getCharacter().get(getCharacter().size() - 1).getCol() + SPEED;
    }

}
