package model;

public class Board {

    private final int row;
    private final int col;
    private Tile[][] board;

    public Board(int row,int col){
        this.row=row;
        this.col=col;
        board=new Tile[row][col];
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }


}
