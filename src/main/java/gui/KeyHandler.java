package gui;

import logic.Game;
import javafx.event.EventHandler;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import model.Board;
import model.character.Cannon;

import static logic.FrameDelay.delay;


public class KeyHandler implements EventHandler<KeyEvent> {

    private static final long CONTROL_DELAY=5;
    private Game game;
    private GraphicsContext gc;

    public KeyHandler(Game game,GraphicsContext gc){
        this.game=game;
        this.gc=gc;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        Cannon cannon= game.getCannon();
        Board board=game.getBoard();
        switch (keyEvent.getCode()) {
        case LEFT:
            cannon.moveLeft();
            break;
        case RIGHT:
            cannon.moveRight();
            break;
        case SPACE:
            game.addProjectile(cannon.fire());
            break;
        default:
            break;
        }
        game.update();
        delay(CONTROL_DELAY);
    }
}
