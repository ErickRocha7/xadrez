package model;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private boolean hasMoved = false;

    public King(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }

    @Override
    public List<Move> getCandidateMoves() {
        List<Move> moves = new ArrayList<>();
        int[][] dirs = {
                { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 },
                { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 }
        };
        for (int[] d : dirs) {
            int r = row + d[0], c = col + d[1];
            if (r >= 0 && r < 8 && c >= 0 && c < 8) {
                if (board.isEmpty(r, c) || board.isOpponentPiece(r, c, color)) {
                    moves.add(new Move(this, r, c, board.isEmpty(r, c) ? MoveType.NORMAL : MoveType.CAPTURE));
                }
            }
        }
        return moves;
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "king.png";
    }
}
