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
    private boolean specialMove;

    public Move(Piece piece, Position startPosition, Position endPosition) {
        this.piece = piece;
        this.endPosition = endPosition;
        this.startPosition = startPosition;
    }

    // Constructor for special move (Castling)
    public Move(Piece piece, Position startPosition, Position endPosition, boolean specialMove) {
        this.piece = piece;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.specialMove = specialMove;
    }

    public boolean executeMove(Board board, boolean whiteTurn, boolean testing) {
        if (whiteTurn) {
            if (board.getTiles()[startPosition.getX()][startPosition.getY()].getPiece().getAlliance() == Alliance.BLACK) {
                return false;
            }
        } else {
            if (board.getTiles()[startPosition.getX()][startPosition.getY()].getPiece().getAlliance() == Alliance.WHITE) {
                return false;
            }
        }

        if (board.getTiles()[startPosition.getX()][startPosition.getY()].getPiece() == null) {
            return false;
        }

        Piece piece = board.getTiles()[startPosition.getX()][startPosition.getY()].getPiece();
        Piece newPiece = board.getTiles()[endPosition.getX()][endPosition.getY()].getPiece();

        piece.setPosition(endPosition);
        board.getTiles()[endPosition.getX()][endPosition.getY()].setPiece(piece);
        board.getTiles()[startPosition.getX()][startPosition.getY()].setPiece(null);

        // Then check if it is check after moving
        if ((whiteTurn && board.IsChecked(Alliance.WHITE)) || (!whiteTurn && board.IsChecked(Alliance.BLACK))) {
            // REVERSE ACTIONS 
            piece.setPosition(startPosition);
            board.getTiles()[startPosition.getX()][startPosition.getY()].setPiece(piece);
            board.getTiles()[endPosition.getX()][endPosition.getY()].setPiece(newPiece);
            return false;
        }

        // If only testing for check or checkmate, undo actions
        if (testing) {
            piece.setPosition(startPosition);
            board.getTiles()[startPosition.getX()][startPosition.getY()].setPiece(piece);
            board.getTiles()[endPosition.getX()][endPosition.getY()].setPiece(newPiece);
        }

        return true;
    }

    public void moveSpecialMove(Board board) {

        // Handle castling moves
        // Long castle
        if (getEndPosition().getX() == 2) {
            Piece piece = board.getTiles()[0][getStartPosition().getY()].getPiece();
            // Set piece to new location
            board.getTiles()[3][getEndPosition().getY()].setPiece(piece);
            // Let's change piece's location information
            board.getTiles()[3][getEndPosition().getY()].getPiece()
                    .setPosition(new Position(3, getEndPosition().getY()));
            // Remove piece from original position
            board.getTiles()[0][getStartPosition().getY()].setPiece(null);

            // short castle
        } else if (getEndPosition().getX() == 6) {
            Piece piece = board.getTiles()[7][getStartPosition().getY()].getPiece();
            // Set piece to new location
            board.getTiles()[5][getEndPosition().getY()].setPiece(piece);
            // Let's change piece's location information
            board.getTiles()[5][getEndPosition().getY()].getPiece()
                    .setPosition(new Position(5, getEndPosition().getY()));
            // Remove piece from original position
            board.getTiles()[7][getStartPosition().getY()].setPiece(null);
        }
    }

    public String toString() {
        return piece.getName() + ", " + startPosition + ", " + endPosition + " " + specialMove;
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

    public boolean isSpecialMove() {
        return specialMove;
    }

    public void setSpecialMove(boolean specialMove) {
        this.specialMove = specialMove;
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
