package model.character;

import javafx.scene.paint.Color;
import model.Tile;
import model.character.projectile.Projectile;


import java.util.ArrayList;

import static model.character.projectile.CannonProjectile.spawnCannonProjectile;

public class Cannon extends CombatCharacter {

    private static final Color COLOR = Color.ORANGE;
    private static final int SPEED=5;

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
        Projectile projectile=null;
        return new Cannon(cannon,new Speed(SPEED),projectile);
    }

    @Override
    public Projectile fire(){
        return spawnCannonProjectile(getCharacter().get(4).getRow()-3,getCharacter().get(4).getCol());
    }

}
