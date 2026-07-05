package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Piece[][] squares = new Piece[8][8];
    private Color turn = Color.WHITE;
    private List<Move> moveHistory = new ArrayList<>();

    public Board() {
        setupPieces();
    }

    private void setupPieces() {
        // Peças pretas
        squares[0][0] = new Rook(Color.BLACK, 0, 0, this);
        squares[0][1] = new Knight(Color.BLACK, 0, 1, this);
        squares[0][2] = new Bishop(Color.BLACK, 0, 2, this);
        squares[0][3] = new Queen(Color.BLACK, 0, 3, this);
        squares[0][4] = new King(Color.BLACK, 0, 4, this);
        squares[0][5] = new Bishop(Color.BLACK, 0, 5, this);
        squares[0][6] = new Knight(Color.BLACK, 0, 6, this);
        squares[0][7] = new Rook(Color.BLACK, 0, 7, this);
        for (int c = 0; c < 8; c++) {
            squares[1][c] = new Pawn(Color.BLACK, 1, c, this);
        }

        // Peças brancas
        for (int c = 0; c < 8; c++) {
            squares[6][c] = new Pawn(Color.WHITE, 6, c, this);
        }
        squares[7][0] = new Rook(Color.WHITE, 7, 0, this);
        squares[7][1] = new Knight(Color.WHITE, 7, 1, this);
        squares[7][2] = new Bishop(Color.WHITE, 7, 2, this);
        squares[7][3] = new Queen(Color.WHITE, 7, 3, this);
        squares[7][4] = new King(Color.WHITE, 7, 4, this);
        squares[7][5] = new Bishop(Color.WHITE, 7, 5, this);
        squares[7][6] = new Knight(Color.WHITE, 7, 6, this);
        squares[7][7] = new Rook(Color.WHITE, 7, 7, this);
    }

    // Métodos utilitários de leitura e escrita
    public Piece getPiece(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) return null;
        return squares[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        squares[row][col] = piece;
    }

    public boolean isEmpty(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8 && squares[row][col] == null;
    }

    public boolean isOpponentPiece(int row, int col, Color myColor) {
        Piece p = getPiece(row, col);
        return p != null && p.getColor() != myColor;
    }

    // Controle de turnos e histórico de lances
    public Color getTurn() {
        return turn;
    }

    public void switchTurn() {
        turn = (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public Move getLastMove() {
        if (moveHistory.isEmpty()) return null;
        return moveHistory.get(moveHistory.size() - 1);
    }

    // Lógica base de execução de jogadas
    public void executeMove(Move move) {
        Piece piece = move.piece;
        int fromRow = piece.getRow(), fromCol = piece.getCol();
        int toRow = move.toRow, toCol = move.toCol;

        squares[fromRow][fromCol] = null;
        Piece captured = getPiece(toRow, toCol);
        move.captured = captured;

        switch (move.type) {
            case KINGSIDE_CASTLE:
                squares[toRow][toCol] = piece;
                piece.setPosition(toRow, toCol);
                Rook rookK = (Rook) getPiece(toRow, 7);
                squares[toRow][5] = rookK;
                squares[toRow][7] = null;
                rookK.setPosition(toRow, 5);
                rookK.setMoved(true);
                ((King) piece).setMoved(true);
                break;
            case QUEENSIDE_CASTLE:
                squares[toRow][toCol] = piece;
                piece.setPosition(toRow, toCol);
                Rook rookQ = (Rook) getPiece(toRow, 0);
                squares[toRow][3] = rookQ;
                squares[toRow][0] = null;
                rookQ.setPosition(toRow, 3);
                rookQ.setMoved(true);
                ((King) piece).setMoved(true);
                break;
            case EN_PASSANT:
                squares[toRow][toCol] = piece;
                piece.setPosition(toRow, toCol);
                int capturedRow = (piece.getColor() == Color.WHITE) ? toRow + 1 : toRow - 1;
                move.captured = squares[capturedRow][toCol];
                squares[capturedRow][toCol] = null;
                break;
            case PROMOTION:
                Piece promo = new Queen(piece.getColor(), toRow, toCol, this);
                squares[toRow][toCol] = promo;
                break;
            default: // NORMAL, CAPTURE
                squares[toRow][toCol] = piece;
                piece.setPosition(toRow, toCol);
                break;
        }

        if (piece instanceof King) {
            ((King) piece).setMoved(true);
        } else if (piece instanceof Rook) {
            ((Rook) piece).setMoved(true);
        }

        moveHistory.add(move);
    }

    public void undoMove(Move move) {
    }

    // Detecção de casas atacadas (Ameaças)
    public boolean isSquareAttacked(int row, int col, Color defenderColor) {
        Color attackerColor = (defenderColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = squares[r][c];
                if (p != null && p.getColor() == attackerColor) {
                    List<Move> moves = p.getCandidateMoves();
                    for (Move m : moves) {
                        if (m.toRow == row && m.toCol == col) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Validação de xeque e movimentos legais
    public boolean isKingInCheck(Color color) {
        int kingRow = -1, kingCol = -1;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = squares[r][c];
                if (p instanceof King && p.getColor() == color) {
                    kingRow = r;
                    kingCol = c;
                    break;
                }
            }
        }
        if (kingRow == -1) return false;
        return isSquareAttacked(kingRow, kingCol, color);
    }

    public boolean isMoveLegal(Move move) {
        Piece piece = move.piece;
        int origRow = piece.getRow(), origCol = piece.getCol();
        Piece captured = getPiece(move.toRow, move.toCol);

        squares[origRow][origCol] = null;
        squares[move.toRow][move.toCol] = piece;
        piece.setPosition(move.toRow, move.toCol);

        boolean kingInCheck = isKingInCheck(piece.getColor());

        squares[origRow][origCol] = piece;
        squares[move.toRow][move.toCol] = captured;
        piece.setPosition(origRow, origCol);

        return !kingInCheck;
    }

    // Filtragem de lances legais
    public List<Move> getAllLegalMoves(Color color) {
        List<Move> legalMoves = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = squares[r][c];
                if (p != null && p.getColor() == color) {
                    for (Move m : p.getCandidateMoves()) {
                        if (isMoveLegal(m)) {
                            legalMoves.add(m);
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    // Verificação de fim de jogo
    public boolean isCheckmate(Color color) {
        if (!isKingInCheck(color)) return false;
        return getAllLegalMoves(color).isEmpty();
    }

    public boolean isStalemate(Color color) {
        if (isKingInCheck(color)) return false;
        return getAllLegalMoves(color).isEmpty();
    }
}