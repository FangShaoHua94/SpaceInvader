package logic;

import gui.Painter;
import javafx.scene.canvas.GraphicsContext;
import model.Board;
import model.character.Alien;
import model.character.Cannon;
import model.character.CombatCharacter;
import model.character.projectile.Projectile;

import java.util.ArrayList;

import static logic.FrameDelay.delay;
import static model.character.Alien.spawnAlien;
import static model.character.Bunker.spawnBunker;
import static model.character.Cannon.spawnCannon;

public class Game implements Runnable {

    private static final int ROW=150;
    private static final int COL=200;
    private static final int BUNKER_COUNT=4;
    private static final long FRAME_DELAY=30;

    private GraphicsContext gc;
    private Board board;
    private int score;
    private Cannon cannon;
    private ArrayList<CombatCharacter> characters;
    private ArrayList<Projectile> projectiles;

    public Game(GraphicsContext gc){
        this.gc=gc;
        board=new Board(ROW,COL);
        characters=new ArrayList<>();
        projectiles=new ArrayList<>();
        score=0;
    }

    public Board getBoard(){
        return board;
    }

    public Cannon getCannon(){
        return cannon;
    }

    @Override
    public void run() {
        initializeGame();
        while(true) {
            Painter.paint(this, gc);
            updateProjectiles();
            delay(FRAME_DELAY);
        }

    }

    private void initializeGame(){
        // add bunker
        for(int i=0;i<BUNKER_COUNT;i++) {
            board.add(spawnBunker(ROW-50,i*50+15).getCharacter());
        }

        // add cannon
        cannon=spawnCannon(ROW-20,COL/2-5);
        characters.add(cannon);
        board.add(cannon.getCharacter());

        // add alien
        for(int i=0;i<4;i++){
            for(int j=0;j<11;j++){
                Alien alien= spawnAlien(i*10+1,j*15+20);
                characters.add(alien);
                board.add(alien.getCharacter());
            }
        }

    }

    public void addProjectile(Projectile projectile){
        if(projectile!=null) {
            projectiles.add(projectile);
            board.add(projectile.getCharacter());
        }
    }

    private void updateProjectiles(){
        ArrayList<Projectile> toBeRemove=new ArrayList<>();
        projectiles.forEach(projectile -> {
            if(withinBoundary(projectile.nextRow(),projectile.getCol())){
                projectile.advance();
            }else {
                toBeRemove.add(projectile);
            }
        });
        removeProjectile(toBeRemove);
    }

    public void update(){
        board.updateBoard();
    }

    public static boolean withinBoundary(int row,int col){
        return row >= 0 && row < ROW && col >= 0 && col < COL;
    }

    private void removeProjectile(ArrayList<Projectile> toBeRemove){
        toBeRemove.forEach(projectile -> {
            board.remove(projectile.getCharacter());
            characters.forEach(character->character.resetFire(projectile));
        });
        projectiles.removeAll(toBeRemove);
        update();
    }

}
