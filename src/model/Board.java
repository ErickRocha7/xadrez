package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Piece[][] squares = new Piece[8][8];
    private Color turn = Color.WHITE;
    private List<Move> moveHistory = new ArrayList<>();

    public Board() {
    }
}
