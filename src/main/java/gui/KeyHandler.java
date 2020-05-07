package gui;

import Logic.Game;
import javafx.event.EventHandler;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;


public class KeyHandler implements EventHandler<KeyEvent> {

    private Game game;
    private GraphicsContext gc;

    public KeyHandler(Game game,GraphicsContext gc){
        this.game=game;
        this.gc=gc;
    }

    @Override
    public void handle(KeyEvent keyEvent) {

    }
}
