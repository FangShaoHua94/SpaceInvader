package model.character;

import javafx.scene.paint.Color;
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

    public boolean contains(Tile tile) {
        return getCharacter().stream().anyMatch(t -> t.equals(tile));
    }

    public static ArrayList<Tile> spawn(int row, int col, int dimension, Color color) {
        ArrayList<Tile> character = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                character.add(new Tile(row + i, col + j, color));
            }
        }
        return character;
    }

}
