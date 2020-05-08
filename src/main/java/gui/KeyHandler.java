package gui;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import logic.Game;
import model.character.Cannon;

import static logic.FrameDelay.controlDelay;

public class KeyHandler implements EventHandler<KeyEvent> {

    private Game game;
    private GraphicsContext gc;

    public KeyHandler(Game game, GraphicsContext gc) {
        this.game = game;
        this.gc = gc;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        Cannon cannon = game.getCannon();
        switch (keyEvent.getCode()) {
        case LEFT:
            if (cannon != null) {
                cannon.moveLeft();
            }
            break;
        case RIGHT:
            if (cannon != null) {
                cannon.moveRight();
            }
            break;
        case SPACE:
            if (cannon != null) {
                game.addProjectile(cannon.fire());
            }
            break;
        case ENTER:
            game.terminate();
            game = new Game(gc);
            (new Thread(game)).start();
        default:
            break;
        }
        game.updateBoard();
        Painter.paint(game, gc);
        controlDelay();
    }

}
