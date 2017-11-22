package com.company.chess.game;

import com.company.chess.board.Board;
import com.company.chess.board.Move;

public class Game {
    
   private Board board;
   private boolean whiteTurn;
   
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

    public boolean movePiece(Move move) {
        if (move.executeMove(board, whiteTurn)) {
            whiteTurn = !whiteTurn;
            return true;
        }
        return false;
    }
   
   
   
     
    

}
