package model.character;

import model.Tile;
import model.character.projectile.Projectile;

import java.util.ArrayList;

public abstract class CombatCharacter extends MovableCharacter{

    private Projectile projectile;

    public CombatCharacter(ArrayList<Tile> character, Speed speed, Projectile projectile){
        super(character,speed);
        this.projectile=projectile;
    }

    public Projectile getProjectile(){
        return projectile;
    }

    public abstract Projectile fire();

}
