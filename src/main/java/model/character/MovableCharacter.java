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

}
