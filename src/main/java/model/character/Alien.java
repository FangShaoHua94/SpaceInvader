package model.character;

import model.Tile;
import model.character.projectile.Projectile;

import java.util.ArrayList;

public class Alien extends CombatCharacter {

    private static final int SPEED= 3;

    public Alien(ArrayList<Tile> alien, Speed speed) {
        super(alien, speed);
    }

    public static Alien spawnAlien(){
        ArrayList<Tile> alien=new ArrayList<>();
        return null;
    }

    @Override
    public Projectile fire() {
        return null;
    }

    @Override
    public int nextLeftCol() {
        return getCharacter().get(0).getCol()-SPEED;
    }

    @Override
    public int nextRightCol() {
        return getCharacter().get(9).getCol()+SPEED;
    }

}
