package model.character;

import javafx.scene.paint.Color;
import model.Tile;
import model.character.projectile.Projectile;

import java.util.ArrayList;

import static model.character.projectile.AlienProjectile.spawnAlienProjectile;

public class Alien extends CombatCharacter {

    private static final Color COLOR = Color.PURPLE;
    private static final int SPEED=1;

    private Alien(ArrayList<Tile> cannon, Speed speed) {
        super(cannon, speed);
    }

    public static Alien spawnAlien(int row,int col){
        ArrayList<Tile> alien=new ArrayList<>();
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                alien.add(new Tile(row+i,col+j,COLOR));
            }
        }
        return new Alien(alien,new Speed(SPEED));
    }

    @Override
    public Projectile fire(){
        System.out.println("fire");
        if(!hasFired()) {
            Projectile projectile =spawnAlienProjectile(getCharacter().get(45).getRow() + 1, getCharacter().get(45).getCol());
            setProjectile(projectile);
            return projectile;
        }
        return null;
    }

    @Override
    public int nextLeftCol() {
        return getCharacter().get(0).getCol()-SPEED;
    }

    @Override
    public int nextRightCol() {
        return getCharacter().get(6).getCol()+SPEED;
    }

    @Override
    public void moveDown(){
        for(int i=0;i<3;i++){
            super.moveDown();
        }
    }

}
