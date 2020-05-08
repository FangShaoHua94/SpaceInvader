package gui;

import logic.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Board;
import model.Tile;

import static model.Tile.DIMENSION;

public class Painter {

    public static void paint(Game game, GraphicsContext gc) {
        paintBoard(game.getBoard(), gc);
    }

    public static void paintBoard(Board board, GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0,
                board.getCol() * DIMENSION, board.getRow() * DIMENSION);
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if (board.getBoard()[i][j] != null) {
                    paintTile(board.getBoard()[i][j], gc);
                }
            }
        }
    }

    public static void paintTile(Tile tile, GraphicsContext gc) {
        gc.setFill(tile.getColor());
        gc.fillRect(tile.getCol() * DIMENSION, tile.getRow() * DIMENSION, DIMENSION, DIMENSION);
    }


}
