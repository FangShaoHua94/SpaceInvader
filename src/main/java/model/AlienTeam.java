package model;

import model.character.Alien;
import model.character.projectile.Projectile;

import static logic.Game.withinBoundary;

public class AlienTeam {

    public static final int ALIEN_ROW=4;
    public static final int ALIEN_COL=11;
    private Alien[][] aliens;
    private Direction direction;
    private int count=0;

    enum Direction{
        LEFT,RIGHT
    }

    public AlienTeam(){
        aliens=new Alien[ALIEN_ROW][ALIEN_COL];
        direction=Direction.RIGHT;
    }

    public void addAlien(Alien alien,int row,int col){
        aliens[row][col]=alien;
    }

    public void resetFire(Projectile projectile){
        for(int i=0;i<ALIEN_ROW;i++){
            for(int j=0;j<ALIEN_COL;j++){
                if(aliens[i][j]!=null) {
                    aliens[i][j].resetFire(projectile);
                }
            }
        }
    }

    public void move(){
        Alien alien;
        System.out.print(count+" ");
        switch (direction){
        case LEFT:
            alien=leftMostAlien();
            if(withinBoundary(alien.getCharacter().get(0).getRow(),alien.nextLeftCol())){
                System.out.println("move left");
                moveLeft();
            }else{
                System.out.println("move down to right");
                direction=Direction.RIGHT;
                moveDown();
            }
            break;
        case RIGHT:
            alien=rightMostAlien();
            if(withinBoundary(alien.getCharacter().get(0).getRow(),alien.nextRightCol())){
                System.out.println("move right");
                moveRight();
            }else{
                System.out.println("move down to left");
                direction=Direction.LEFT;
                moveDown();
            }
            break;
        default:
            break;
        }
        count++;
    }

    private void moveLeft(){
        for(int i=0;i<ALIEN_ROW;i++){
            for(int j=0;j<ALIEN_COL;j++){
                if(aliens[i][j]!=null) {
                    aliens[i][j].moveLeft();
                }
            }
        }
    }

    private void moveRight(){
        for(int i=0;i<ALIEN_ROW;i++){
            for(int j=0;j<ALIEN_COL;j++){
                if(aliens[i][j]!=null) {
                    aliens[i][j].moveRight();
                }
            }
        }
    }

    private void moveDown(){
        for(int i=0;i<ALIEN_ROW;i++){
            for(int j=0;j<ALIEN_COL;j++){
                if(aliens[i][j]!=null) {
                    aliens[i][j].moveDown();
                }
            }
        }
    }

    private Alien leftMostAlien(){
        for(int i=0;i<ALIEN_COL;i++){
            for(int j=0;j<ALIEN_ROW;j++){
                if(aliens[j][i]!=null){
                    return aliens[j][i];
                }
            }
        }
        return null;
    }

    private Alien rightMostAlien(){
        for(int i=ALIEN_COL-1;i>=0;i--){
            for(int j=0;j<ALIEN_ROW;j++){
                if(aliens[j][i]!=null){
                    return aliens[j][i];
                }
            }
        }
        return null;
    }
}
