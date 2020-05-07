package model.character;

import model.Tile;

import java.util.ArrayList;

public class Alien extends CombatCharacter {

    public Alien(ArrayList<Tile> alien, Speed speed, Projectile projectile) {
        super(alien, speed, projectile);
    }

    public static Alien spawnAlien(){
        ArrayList<Tile> alien=new ArrayList<>();
        return null;
    }


}
