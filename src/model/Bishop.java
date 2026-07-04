package model;

public class Bishop extends Piece {
    public Bishop(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "bishop.png";
    }
}
