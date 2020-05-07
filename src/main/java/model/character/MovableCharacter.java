package model.character;

import model.Tile;

import java.util.ArrayList;

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
            getCharacter().forEach(tile->tile.moveRight());
        }
    }

    public void moveLeft(){
        for(int i=0;i<speed.getSpeed();i++){
            getCharacter().forEach(tile->tile.moveLeft());
        }
    }

    public void moveUp(){
        for(int i=0;i<speed.getSpeed();i++){
            getCharacter().forEach(tile->tile.moveUp());
        }
    }

    public void moveDown(){
        for(int i=0;i<speed.getSpeed();i++){
            getCharacter().forEach(tile->tile.moveDown());
        }
    }



}
