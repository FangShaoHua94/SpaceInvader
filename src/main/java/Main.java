import logic.Game;
import gui.KeyHandler;
import gui.Painter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static double HEIGHT = 600;
    private static double WIDTH = 800;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Game game = new Game(gc);
        Painter.paint(game, gc);
        canvas.setOnKeyPressed(new KeyHandler(game, gc));
        canvas.setFocusTraversable(true);
        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Space Invader");
        stage.setResizable(false);
        stage.show();
        (new Thread(game)).start();
    }
}
