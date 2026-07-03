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

        // Roque
        if (!hasMoved && !board.isKingInCheck(color)) {
            // Roque curto (lado do rei)
            if (canCastleKingside()) {
                moves.add(new Move(this, row, 6, MoveType.KINGSIDE_CASTLE));
            }
            // Roque longo (lado da dama)
            if (canCastleQueenside()) {
                moves.add(new Move(this, row, 2, MoveType.QUEENSIDE_CASTLE));
            }
        }
        return moves;
    }

    private boolean canCastleKingside() {
        int r = row;
        if (!board.isEmpty(r, 5) || !board.isEmpty(r, 6))
            return false;
        Piece corner = board.getPiece(r, 7);
        if (!(corner instanceof Rook) || corner.getColor() != color)
            return false;
        Rook rook = (Rook) corner;
        if (rook.hasMoved())
            return false;
        return !board.isSquareAttacked(r, 4, color) &&
                !board.isSquareAttacked(r, 5, color) &&
                !board.isSquareAttacked(r, 6, color);
    }

    private boolean canCastleQueenside() {
        int r = row;
        if (!board.isEmpty(r, 1) || !board.isEmpty(r, 2) || !board.isEmpty(r, 3))
            return false;
        Piece corner = board.getPiece(r, 0);
        if (!(corner instanceof Rook) || corner.getColor() != color)
            return false;
        Rook rook = (Rook) corner;
        if (rook.hasMoved())
            return false;
        return !board.isSquareAttacked(r, 4, color) &&
                !board.isSquareAttacked(r, 3, color) &&
                !board.isSquareAttacked(r, 2, color);
    }

    @Override
    public String getImageFileName() {
        return "pieces/" + (color == Color.WHITE ? "white_" : "black_") + "king.png";
    }
}
