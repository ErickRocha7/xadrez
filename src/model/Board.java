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
        if (row < 0 || row >= 8 || col < 0 || col >= 8)
            return null;
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
        if (moveHistory.isEmpty())
            return null;
        return moveHistory.get(moveHistory.size() - 1);
    }
}