package model.character;

import model.Tile;

import java.util.ArrayList;

public class Cannon extends CombatCharacter {

    public Cannon(ArrayList<Tile> cannon, Speed speed , Projectile projectile) {
        super(cannon, speed, projectile);
    }

    public static Cannon spawnCannon(){
        ArrayList<Tile> cannon=new ArrayList<>();
        return null;
    }

}
