package model;

public class Score {

    private static final int STARTING_SCORE = 0;
    private int score;

    public Score(int score) {
        this.score = score;
    }

    public static Score startingScore() {
        return new Score(STARTING_SCORE);
    }

    public int getScore() {
        return score;
    }

    public void addPoint(Score score) {
        this.score += score.getScore();
    }

}
