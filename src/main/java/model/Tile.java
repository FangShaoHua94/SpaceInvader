package model;


import javafx.scene.paint.Color;

public class Tile {

    public static int DIMENSION =4;
    private Color color;
    private int row;
    private int col;

    public Tile(int row,int col,Color color){
        this.row=row;
        this.col=col;
        this.color=color;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public Color getColor(){
        return color;
    }

    public void moveRight(){
        col+=5;
    }

    public void moveLeft(){
        col-=5;
    }
}
