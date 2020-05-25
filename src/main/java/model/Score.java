package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Score {

    private IntegerProperty score;

    public Score(){
        score = new SimpleIntegerProperty(0);
    }

    public int getScore(){
        return score.getValue();
    }

    public IntegerProperty integerProperty(){
        return score;
    }


}
