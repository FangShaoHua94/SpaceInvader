package gui;

import logic.Game;
import javafx.event.EventHandler;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import model.character.Cannon;


public class KeyHandler implements EventHandler<KeyEvent> {

    private Game game;
    private GraphicsContext gc;

    public KeyHandler(Game game,GraphicsContext gc){
        this.game=game;
        this.gc=gc;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        Cannon cannon= game.getCannon();

        switch (keyEvent.getCode()) {
        case LEFT:
            cannon.getCharacter().forEach(tile->tile.moveLeft());
            break;
        case RIGHT:
            cannon.getCharacter().forEach(tile->tile.moveRight());
            break;
        default:
            break;
        }
        game.update();
    }
}
