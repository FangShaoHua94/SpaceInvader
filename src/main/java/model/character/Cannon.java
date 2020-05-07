package model.character;

import javafx.scene.paint.Color;
import model.Tile;


import java.util.ArrayList;

public class Cannon extends CombatCharacter {

    private static final Color COLOR = Color.ORANGE;

    private Cannon(ArrayList<Tile> cannon, Speed speed , Projectile projectile) {
        super(cannon, speed, projectile);
    }

    public static Cannon spawnCannon(int row,int col){
        ArrayList<Tile> cannon=new ArrayList<>();
        for(int i=0;i<5;i++){
            for(int j=0;j<10;j++){
                cannon.add(new Tile(row+i,col+j,COLOR));
            }
        }
        Speed speed=null;
        Projectile projectile=null;
        return new Cannon(cannon,speed,projectile);
    }

}
