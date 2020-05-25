import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.Live;
import model.Score;

public class Game {

    private Timeline animation;
    private Score score;
    private Live live;

    public Game(){
        score= new Score();
        live= new Live();

    }

    public void start(){
        animation = new Timeline(
                new KeyFrame(new Duration(60), t->{
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

}
