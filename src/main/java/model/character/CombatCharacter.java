package model.character;

import model.Tile;
import model.character.projectile.Projectile;

import java.util.ArrayList;

import static logic.Game.withinBoundary;

public abstract class CombatCharacter extends MovableCharacter{

    private Projectile projectile;
    private boolean fired;

    public CombatCharacter(ArrayList<Tile> character, Speed speed){
        super(character,speed);
        projectile=null;
        fired=false;
    }

    public void setProjectile(Projectile projectile){
        this.projectile=projectile;
        fired=true;
    }

    public Projectile getProjectile(){
        return projectile;
    }

    public abstract Projectile fire();

    protected boolean hasFired(){
        return fired;
    }

    public void resetFire(Projectile projectile){
        if(fired && this.projectile.equals(projectile)){
            fired=false;
        }
    }

    public void moveLeft(){
        if(withinBoundary(getCharacter().get(0).getRow(),nextLeftCol())){
            super.moveLeft();
        }
    }

    public void moveRight(){
        if(withinBoundary(getCharacter().get(0).getRow(),nextRightCol())){
            super.moveRight();
        }
    }

    public abstract int nextLeftCol();

    public abstract int nextRightCol();

}
