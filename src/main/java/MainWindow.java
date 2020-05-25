import javafx.beans.property.SimpleStringProperty;
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
    public void initialize() {
        game = new Game();
        getChildren().add(game.getComponents());
        setBinding();
        game.start();
    }

    private void setBinding() {
        score.textProperty().bind(new SimpleStringProperty("Score: ")
                .concat(game.getScoreIntegerProperty()));
        live.textProperty().bind(new SimpleStringProperty("Live: ")
                .concat(game.getLiveIntegerProperty()));
    }


}
