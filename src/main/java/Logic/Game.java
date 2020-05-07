package Logic;

import javafx.scene.canvas.GraphicsContext;
import model.Board;

public class Game implements Runnable {

    private static final int ROW=300;
    private static final int COL=400;
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

    }
}
