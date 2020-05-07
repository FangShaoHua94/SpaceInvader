package gui;

import Logic.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Board;

import static model.Tile.DIMENSION;


public class Painter {

    public static void paint(Game game, GraphicsContext gc){
        paintBoard(game.getBoard(),gc);
    }

    public static void paintBoard(Board board, GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0,
                board.getCol() * DIMENSION, board.getRow() * DIMENSION);
    }


}
