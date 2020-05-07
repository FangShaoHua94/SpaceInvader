package logic;

import gui.Painter;
import javafx.scene.canvas.GraphicsContext;
import model.Board;
import static model.character.Bunker.spawnBunker;

public class Game implements Runnable {

    private static final int ROW=150;
    private static final int COL=200;
    private static final int BUNKER_COUNT=4;

    private GraphicsContext gc;
    private Board board;
    private int score;

    public Game(GraphicsContext gc){
        this.gc=gc;
        board=new Board(ROW,COL);
        score=0;
    }

    public Board getBoard(){
        return board;
    }

    @Override
    public void run() {
        initializeGame();
        Painter.paint(this,gc);
    }

    private void initializeGame(){
        for(int i=0;i<BUNKER_COUNT;i++) {
            board.add(spawnBunker(ROW-50,i*50+15).getCharacter());
        }
    }
}
