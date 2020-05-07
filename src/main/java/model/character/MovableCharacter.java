package model.character;

import model.Tile;

import java.util.ArrayList;

import static logic.Game.withinBoundary;

public abstract class MovableCharacter extends Character {

    private Speed speed;

    public MovableCharacter(ArrayList<Tile> character, Speed speed) {
        super(character);
        this.speed = speed;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void moveRight(){
        for(int i=0;i<speed.getSpeed();i++){
            getCharacter().forEach(Tile::moveRight);
        }
    }

    public void moveLeft(){
        for(int i=0;i<speed.getSpeed();i++){
            getCharacter().forEach(Tile::moveLeft);
        }
    }

    public void moveUp(){
        for(int i=0;i<speed.getSpeed();i++){
            getCharacter().forEach(Tile::moveUp);
        }
    }

    public void moveDown(){
        for(int i=0;i<speed.getSpeed();i++){
            getCharacter().forEach(Tile::moveDown);
        }
    }

}
