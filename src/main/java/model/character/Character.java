package model.character;

import model.Tile;

import java.util.ArrayList;

public abstract class Character {

    private ArrayList<Tile> character;

    public Character(ArrayList<Tile> character) {
        this.character = character;
    }

    public ArrayList<Tile> getCharacter() {
        return character;
    }

}
