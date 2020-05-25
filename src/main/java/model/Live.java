package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Live {

    private IntegerProperty numOfLive;

    public Live() {
        numOfLive = new SimpleIntegerProperty(3);
    }

    public int getNumOfLive() {
        return numOfLive.getValue();
    }

    public IntegerProperty integerProperty() {
        return numOfLive;
    }

    public void loseLive() {
        numOfLive.setValue(numOfLive.getValue() - 1);
    }

}
