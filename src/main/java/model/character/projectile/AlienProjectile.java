package model.character.projectile;

import javafx.scene.paint.Color;
import model.Tile;
import model.character.Speed;

import java.util.ArrayList;

public class AlienProjectile extends Projectile{

    protected static final Color COLOR= Color.RED;
    private static final int SPEED = 2;

    public AlienProjectile(ArrayList<Tile> projectile, Speed speed){
        super(projectile,speed);
    }

    public static Projectile spawnAlienProjectile(int row,int col){
        ArrayList<Tile> projectile =new ArrayList<>();
        for (int i=0;i<4;i++){
            for(int j=0;j<1;j++){
                projectile.add(new Tile(row+i,col+j,COLOR));
            }
        }
        return new AlienProjectile(projectile,new Speed(SPEED));
    }

    @Override
    public void advance() {
        moveDown();
    }

    @Override
    public int nextRow() {
        return getCharacter().get(0).getRow()+SPEED;
    }

    @Override
    public Projectile duplicate() {
        ArrayList<Tile> duplicate =new ArrayList<>();
        getCharacter().forEach(tile -> duplicate.add(new Tile(tile.getRow(),tile.getCol(),tile.getColor())));
        return new AlienProjectile(duplicate,new Speed(SPEED));
    }

}
