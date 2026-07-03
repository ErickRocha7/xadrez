package model;

public class Move {
    public Piece piece;
    public int fromRow, fromCol;
    public int toRow, toCol;
    public MoveType type;
    public Piece captured;
    public Piece promotionPiece; // apenas para promoção

    public Move(Piece piece, int toRow, int toCol, MoveType type) {
        this.piece = piece;
        this.fromRow = piece.getRow();
        this.fromCol = piece.getCol();
        this.toRow = toRow;
        this.toCol = toCol;
        this.type = type;
    }
}
