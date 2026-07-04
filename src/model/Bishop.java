package model;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }

    @Override
    public List<Move> getCandidateMoves() {
        List<Move> moves = new ArrayList<>();
        int[][] dirs = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
        for (int[] d : dirs) {
            int r = row + d[0], c = col + d[1];
            while (r >= 0 && r < 8 && c >= 0 && c < 8) {
                if (board.isEmpty(r, c)) {
                    moves.add(new Move(this, r, c, MoveType.NORMAL));
                } else {
                    if (board.isOpponentPiece(r, c, color)) {
                        moves.add(new Move(this, r, c, MoveType.CAPTURE));
                    }
                    break;
                }
                r += d[0];
                c += d[1];
            }
        }
        return moves;
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "bishop.png";
    }
}
