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

    public Color getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPosition(int r, int c) {
        row = r;
        col = c;
    }
}
