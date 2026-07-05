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
        int startRow = (color == Color.WHITE) ? 6 : 1;
        int promoRow = (color == Color.WHITE) ? 0 : 7;

        int oneStep = row + direction;
        if (oneStep >= 0 && oneStep < 8 && board.isEmpty(oneStep, col)) {
            if (oneStep == promoRow) {
                moves.add(new Move(this, oneStep, col, MoveType.PROMOTION));
            } else {
                moves.add(new Move(this, oneStep, col, MoveType.NORMAL));
            }

            if (row == startRow) {
                int twoStep = row + 2 * direction;
                if (board.isEmpty(twoStep, col)) {
                    moves.add(new Move(this, twoStep, col, MoveType.NORMAL));
                }
            }
        }

        for (int dc : new int[] { -1, 1 }) {
            int newCol = col + dc;
            if (newCol >= 0 && newCol < 8) {
                int newRow = row + direction;
                if (newRow >= 0 && newRow < 8) {
                    if (board.isOpponentPiece(newRow, newCol, color)) {
                        if (newRow == promoRow) {
                            moves.add(new Move(this, newRow, newCol, MoveType.PROMOTION));
                        } else {
                            moves.add(new Move(this, newRow, newCol, MoveType.CAPTURE));
                        }
                    }
                }
            }
        }

        // En passant
        Move lastMove = board.getLastMove();
        if (lastMove != null && lastMove.piece instanceof Pawn &&
                Math.abs(lastMove.toRow - lastMove.fromRow) == 2 &&
                lastMove.piece.getColor() != color &&
                lastMove.toRow == row &&
                Math.abs(lastMove.toCol - col) == 1) {
            int epRow = row + direction;
            moves.add(new Move(this, epRow, lastMove.toCol, MoveType.EN_PASSANT));
        }

        return moves;
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "pawn.png";
    }
}
