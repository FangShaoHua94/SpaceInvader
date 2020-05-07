package model.character;

import model.Tile;

import java.util.ArrayList;

public class Projectile extends MovableCharacter {

    public Projectile(ArrayList<Tile> projectile, Speed speed) {
        super(projectile, speed);
    }

//    public static Projectile spawnAlienProjectile(){
//        ArrayList<Tile> projectile =new ArrayList<>();
//
//        return new Projectile(projectile,speed);
//    }

}
