package model;

public class King extends Piece {

    public King(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "king.png";
    }
}
