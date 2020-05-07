package model.character.projectile;

import javafx.scene.paint.Color;
import model.Tile;
import model.character.MovableCharacter;
import model.character.Speed;


import java.util.ArrayList;

public abstract class Projectile extends MovableCharacter {

    public Projectile(ArrayList<Tile> projectile, Speed speed) {
        super(projectile, speed);
    }

    public abstract void advance();

}
