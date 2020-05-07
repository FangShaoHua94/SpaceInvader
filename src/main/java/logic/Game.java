package logic;

import gui.Painter;
import javafx.scene.canvas.GraphicsContext;
import model.Board;
import model.character.Cannon;

import static logic.FrameDelay.delay;
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

    public Game(GraphicsContext gc){
        this.gc=gc;
        board=new Board(ROW,COL);
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
        board.add(cannon.getCharacter());
    }

    public void update(){
        board.updateBoard();
    }
}
