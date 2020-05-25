import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Group;
import javafx.util.Duration;
import model.Live;
import model.Score;
import model.character.Cannon;

import static model.character.Bunker.createBunkers;
import static model.character.Cannon.createCannon;

public class Game {

    private Timeline animation;
    private Score score;
    private Live live;
    private Group characters;

    public Game() {
        score = new Score();
        live = new Live();
        characters = new Group();
        setCharacters();
        bindControl();
    }

    public void start() {
        animation = new Timeline(
                new KeyFrame(new Duration(1000), t -> {
                    checkCollision();
                    System.out.println("running");
                    move();
                })
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.playFromStart();
    }

    private void setCharacters() {
        characters.getChildren().addAll(createBunkers());
        Cannon cannon = createCannon();
        characters.getChildren().add(cannon);
    }

    private void bindControl() {
        characters.setFocusTraversable(true);
        characters.setOnKeyPressed(k -> {

        });
    }

    private void checkCollision() {

    }

    private void move() {

    }

    public Group getComponents() {
        return characters;
    }


    public IntegerProperty getLiveIntegerProperty() {
        return live.integerProperty();
    }

    public IntegerProperty getScoreIntegerProperty() {
        return score.integerProperty();
    }

}
