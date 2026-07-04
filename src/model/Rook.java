package model;

public class Rook extends Piece {

    public Rook(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "rook.png";
    }
}
