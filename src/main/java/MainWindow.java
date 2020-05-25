import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainWindow extends BorderPane {

    @FXML
    private Label score;

    @FXML
    private Label live;

    private Game game;

    @FXML
    public void initialize(){
        game=new Game();
        game.start();
    }








}
