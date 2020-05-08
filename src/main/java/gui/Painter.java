package gui;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Board;
import model.Live;
import model.Score;
import model.Tile;


import static model.Tile.DIMENSION;

public class Painter {

    private static final String SCORE_TEXT = "Score: \t\t %d";
    private static final String LIVE_TEXT = "Live left: \t\t %d";
    private static final Font DISPLAY_FONT = Font.font("Verdana", FontWeight.EXTRA_BOLD, 15);

    public static void paint(Game game, GraphicsContext gc) {
        paintBoard(game.getBoard(), gc);
        paintStats(game.getScore(),game.getLive(), gc);
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

    public static void paintStats(Score score, Live live, GraphicsContext gc){
        gc.setFont(DISPLAY_FONT);
        gc.setFill(Color.SILVER);
        gc.fillText(String.format(SCORE_TEXT, score.getScore()),
                50, 570);
        gc.fillText(String.format(LIVE_TEXT, live.getLive()),
                500, 570);
    }

}
