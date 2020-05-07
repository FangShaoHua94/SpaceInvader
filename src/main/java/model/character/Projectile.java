package model.character;

import javafx.scene.paint.Color;
import model.Tile;


import java.util.ArrayList;

public class Projectile extends MovableCharacter {

    private static final Color COLOR= Color.WHITE;
    private static final int SPEED = 10;

    public Projectile(ArrayList<Tile> projectile, Speed speed) {
        super(projectile, speed);
    }

    public static Projectile spawnCannonProjectile(int row,int col){
        ArrayList<Tile> projectile =new ArrayList<>();
        for (int i=0;i<3;i++){
            for(int j=0;j<1;j++){
                projectile.add(new Tile(row+i,col+j,COLOR));
            }
        }
        return new Projectile(projectile,new Speed(SPEED));
    }

}
