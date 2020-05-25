package model.character;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Bunker extends Character{

    private Bunker(double layoutX, double layoutY) {
        super(layoutX, layoutY);
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                add(new Rectangle(2,2, Color.RED),j,i);
            }
        }
    }

    public static ArrayList<Character> createBunkers(){
        ArrayList<Character> bunkers = new ArrayList<>();
        for(int i=0;i<4;i++){
            bunkers.add(new Bunker(i*150+50,250));
        }
        return bunkers;
    }


}
