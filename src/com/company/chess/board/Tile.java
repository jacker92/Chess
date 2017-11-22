package com.company.chess.board;

import com.company.chess.pieces.Piece;
import com.company.chess.pieces.Position;

public class Tile {
    
    private Piece piece;
    private Position pos;
    
    public Tile(Position pos, Piece piece) {
        this.piece = piece;
        this.pos = pos;
    }
    
    public Tile(Position pos) {
        this.piece = null;
        this.pos = pos;
    }

    public Piece getPiece() {
        return piece;
    }
    
    public Position getPosition() {
        return pos;
    }
    
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
    
    

}
