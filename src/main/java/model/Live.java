package model;

public class Live {

    private static final int STARTING_LIVE = 3;
    private int live;

    private Live(int live) {
        this.live = live;
    }

    public static Live startingLive() {
        return new Live(STARTING_LIVE);
    }

    public int getLive() {
        return live;
    }

    public void reduceLive() {
        live--;
    }

    public boolean noMoreLive() {
        return live == 0;
    }
}
