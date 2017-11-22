package com.company.chess.board;

import com.company.chess.pieces.Alliance;
import com.company.chess.pieces.Piece;
import com.company.chess.pieces.PieceName;
import com.company.chess.pieces.Position;
import java.util.Objects;

public class Move {
    
    private Piece piece;
    private Position endPosition;
    private Position startPosition;
    
    public Move(Piece piece, Position startPosition, Position endPosition) {
        this.piece = piece;
        this.endPosition = endPosition;
        this.startPosition = startPosition;
    }
    
    public boolean executeMove(Board board, boolean whiteTurn) {
        if(whiteTurn) {
        if(board.getTiles()[startPosition.getX()][startPosition.getY()].getPiece().getAlliance() == Alliance.BLACK) {
            return false;
        }
        } else {
            if(board.getTiles()[startPosition.getX()][startPosition.getY()].getPiece().getAlliance() == Alliance.WHITE) {
                return false;
            }
        }
        
        if(board.getTiles()[startPosition.getX()][startPosition.getY()].getPiece() == null) {
            return false;
        } 
        
        Piece piece = board.getTiles()[startPosition.getX()][startPosition.getY()].getPiece();
        piece.setPosition(new Position(endPosition.getX(), endPosition.getY()));
        board.getTiles()[endPosition.getX()][endPosition.getY()].setPiece(piece);
        board.getTiles()[startPosition.getX()][startPosition.getY()].setPiece(null);
        return true;
    }
    
    public String toString() {
        return piece.getName() + ", " + startPosition + ", " + endPosition;
    }
    
    public Move setPieceName(PieceName name) {
      piece.setName(name);
      return this;
    }

    public Piece getPiece() {
        return piece;
    }

    public Move setPiece(Piece piece) {
        this.piece = piece;
        return this;
    }
    
    public Position getEndPosition() {
        return endPosition;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Move other = (Move) obj;
        if (!Objects.equals(this.piece, other.piece)) {
            return false;
        }
        if (!Objects.equals(this.endPosition, other.endPosition)) {
            return false;
        }
        if (!Objects.equals(this.startPosition, other.startPosition)) {
            return false;
        }
        return true;
    }
    
    
    
    
    

}
