package model.character;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class Character extends GridPane {

    public Character(double layoutX, double layoutY){
        setLayoutX(layoutX);
        setLayoutY(layoutY);
    }



}
