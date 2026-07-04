package model;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }

    @Override
    public List<Move> getCandidateMoves() {
        List<Move> moves = new ArrayList<>();
        int[][] jumps = {
                { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 },
                { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }
        };
        for (int[] j : jumps) {
            int r = row + j[0], c = col + j[1];
            if (r >= 0 && r < 8 && c >= 0 && c < 8) {
                if (board.isEmpty(r, c)) {
                    moves.add(new Move(this, r, c, MoveType.NORMAL));
                } else if (board.isOpponentPiece(r, c, color)) {
                    moves.add(new Move(this, r, c, MoveType.CAPTURE));
                }
            }
        }
        return moves;
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "knight.png";
    }
}
