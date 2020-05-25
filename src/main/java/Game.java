import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.util.Duration;
import model.Board;
import model.Live;
import model.Score;
import model.character.Character;

import java.util.ArrayList;

import static model.character.Bunker.createBunkers;


public class Game {

    private Board board;
    private Timeline animation;
    private Score score;
    private Live live;

    public Game(){
        board = new Board();
        score= new Score();
        live= new Live();

    }

    public void start(){
        animation = new Timeline(
                new KeyFrame(new Duration(1000), t->{
                    checkCollision();
                    System.out.println("running");
                    move();
                })
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.playFromStart();
    }

    private void checkCollision(){

    }

    private void move(){

    }

    public ArrayList<Character> getComponents(){
        return createBunkers();
    }

    public IntegerProperty getLiveIntegerProperty(){
        return live.integerProperty();
    }

    public IntegerProperty getScoreIntegerProperty(){
        return score.integerProperty();
    }

}
