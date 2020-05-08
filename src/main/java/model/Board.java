package model;

import java.util.ArrayList;

public class Board {

    private final int row;
    private final int col;
    private Tile[][] board;

    public Board(int row, int col) {
        this.row = row;
        this.col = col;
        board = new Tile[row][col];
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void add(Tile tile) {
        board[tile.getRow()][tile.getCol()] = tile;
    }

    public void add(ArrayList<Tile> character) {
        character.forEach(tile -> add(tile));
    }

    public ArrayList<Tile> collision(ArrayList<Tile> character) {
        ArrayList<Tile> collide = new ArrayList<>();
        for (Tile tile : character) {
            if (board[tile.getRow()][tile.getCol()] != null) {
                collide.add(board[tile.getRow()][tile.getCol()]);
            }
        }
        return collide;
    }

    public Tile[][] getBoard() {
        return board;
    }

    // before and after array list to update rather than check the all board to update
    // before to set them to null, after to reset their pos
    public void updateBoard() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] != null) {
                    tiles.add(board[i][j]);
                }
            }
        }
        board = new Tile[row][col];
        tiles.forEach(block -> board[block.getRow()][block.getCol()] = block);
    }

    public void destroy(Tile tile) {
        board[tile.getRow()][tile.getCol()] = null;
    }

    public void destroy(ArrayList<Tile> tiles) {
        tiles.forEach(this::destroy);
    }

}
