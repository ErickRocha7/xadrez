package model;

import java.util.List;

public abstract class Piece {
    protected Color color;
    protected int row, col;
    protected Board board;

    public Piece(Color color, int row, int col, Board board) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.board = board;
    }

    public Color getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPosition(int r, int c) {
        row = r;
        col = c;
    }

    // Movimentos candidatos sem verificação de xeque
    public abstract List<Move> getCandidateMoves();

    // Retorna o caminho relativo da imagem, ex: "pieces/white_king.png"
    public abstract String getImageFileName();
}
