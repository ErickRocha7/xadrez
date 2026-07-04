package model;

public class Rook extends Piece {
    private boolean hasMoved = false;

    public Rook(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "rook.png";
    }
}
