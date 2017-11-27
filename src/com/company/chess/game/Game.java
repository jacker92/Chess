package com.company.chess.game;

import com.company.chess.board.Board;
import com.company.chess.board.Move;
import com.company.chess.pieces.Alliance;
import com.company.chess.pieces.PieceName;
import com.company.chess.pieces.Position;
import com.company.chess.pieces.Queen;

public class Game {

    private Board board;
    private boolean whiteTurn;
    private boolean pawnIsPromotionable;
    private Position pawnPosition;
    private Alliance pawnAlliance;

    public Game(Board board) {
        this.board = board;
        this.whiteTurn = true;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public boolean movePiece(Move move, boolean testing) {
        if (move.executeMove(board, whiteTurn, testing)) {
            checkPawnPromotion(move);
            whiteTurn = !whiteTurn;
            return true;
        }
        return false;
    }

    private void checkPawnPromotion(Move move) {
        // If executed, and piece is pawn which is positioned at top or bottom of the board,
        // then change piece to queen
        int x = move.getEndPosition().getX();
        int y = move.getEndPosition().getY();

        if (move.getPiece().getName() == PieceName.PAWN && (y == 0 || y == 7)) {
            board.getTiles()[x][y].setPiece(new Queen(x, y, move.getPiece().getAlliance()));
            pawnIsPromotionable = true;
            pawnPosition = new Position(x, y);
            pawnAlliance = move.getPiece().getAlliance();
        }
    }

    public boolean isPawnIsPromotionable() {
        return pawnIsPromotionable;
    }

    public void setPawnIsPromotionable(boolean pawnIsPromotionable) {
        this.pawnIsPromotionable = pawnIsPromotionable;
    }

    public Position getPawnPosition() {
        return pawnPosition;
    }

    public void setPawnPosition(Position pawnPosition) {
        this.pawnPosition = pawnPosition;
    }

    public Alliance getPawnAlliance() {
        return pawnAlliance;
    }

    public void setPawnAlliance(Alliance pawnAlliance) {
        this.pawnAlliance = pawnAlliance;
    }
    
    
    
    

}
