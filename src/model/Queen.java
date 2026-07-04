package model;

public class Queen extends Piece {
    public Queen(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "queen.png";
    }
}
