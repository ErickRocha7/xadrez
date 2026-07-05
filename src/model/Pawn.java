package model;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color, int row, int col, Board board) {
        super(color, row, col, board);
    }

    @Override
    public List<Move> getCandidateMoves() {
        List<Move> moves = new ArrayList<>();
        int direction = (color == Color.WHITE) ? -1 : 1;

        int oneStep = row + direction;
        if (oneStep >= 0 && oneStep < 8 && board.isEmpty(oneStep, col)) {
            moves.add(new Move(this, oneStep, col, MoveType.NORMAL));
        }

        return moves;
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "pawn.png";
    }
}
