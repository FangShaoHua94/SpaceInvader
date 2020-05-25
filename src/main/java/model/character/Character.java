package model.character;

import javafx.scene.layout.GridPane;

public abstract class Character extends GridPane {

    public static final int PIXEL = 2;

    public Character(double layoutX, double layoutY) {
        setLayoutX(layoutX);
        setLayoutY(layoutY);
    }

}
