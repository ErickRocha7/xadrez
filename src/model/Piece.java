package model;

public abstract class Piece {
    protected Color color;
    protected int row, col;
    protected Board board;

    public Piece(Color color, int row, int col, Board board) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.board = board;
    }
}
