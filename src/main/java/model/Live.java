package model;

public class Live {

    private static final int STARTING_LIVE=3;
    private int live;

    public Live(){
        live=STARTING_LIVE;
    }

    public int getLive(){
        return live;
    }

    public void reduceLive(){
        live--;
    }
}
