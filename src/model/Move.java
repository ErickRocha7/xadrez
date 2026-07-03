package model;

public class Move {
    public Piece piece;
    public int fromRow, fromCol;
    public int toRow, toCol;
    public MoveType type;

    public Move(Piece piece, int toRow, int toCol, MoveType type) {
        this.piece = piece;
        this.fromRow = piece.getRow();
        this.fromCol = piece.getCol();
        this.toRow = toRow;
        this.toCol = toCol;
        this.type = type;
    }
}
