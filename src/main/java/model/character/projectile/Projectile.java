package model.character.projectile;

import model.Tile;
import model.character.MovableCharacter;
import model.character.Speed;


import java.util.ArrayList;

public abstract class Projectile extends MovableCharacter {

    public Projectile(ArrayList<Tile> projectile, Speed speed) {
        super(projectile, speed);
    }

    public abstract void advance();

    public abstract int nextRow();

    public Projectile nextPos() {
        Projectile projectile = duplicate();
        projectile.advance();
        return projectile;
    }

    public abstract Projectile duplicate();

    public int getCol() {
        return getCharacter().get(0).getCol();
    }

}
