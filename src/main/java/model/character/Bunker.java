package model.character;

import javafx.scene.paint.Color;
import model.Tile;

import java.util.ArrayList;

public class Bunker extends Character {

    private static final Color COLOR= Color.GREEN;

    private Bunker(ArrayList<Tile> bunker) {
        super(bunker);
    }

    public static Bunker spawnBunker(int row,int col){
        ArrayList<Tile> bunker=new ArrayList<>();
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                bunker.add(new Tile(row+i,col+j,COLOR));
            }
        }
        return new Bunker(bunker);
    }

}
