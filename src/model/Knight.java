package model;

public class Knight extends Piece {
    public Knight(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "knight.png";
    }
}
