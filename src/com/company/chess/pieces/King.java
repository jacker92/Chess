package com.company.chess.pieces;

import com.company.chess.board.Board;
import com.company.chess.board.Move;
import com.company.chess.pieces.movesbank.MovesBank;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(int x, int y, Alliance alliance) {
        super(new Position(x, y), alliance, PieceName.KING);
    }

    @Override
    public List<Move> showAllAvailableMoves(Board board) {
        List<Move> list = new ArrayList<>();

        int currentX = this.position.getX();
        int currentY = this.position.getY();
        
         for (int i = 0; i < 8; i++) {
            int possibleX = currentX+MovesBank.kingPositions[i].getX();
            int possibleY = currentY+MovesBank.kingPositions[i].getY();
            
            // If x or y is out of bounds, then continue to next value
            if(possibleX < 0 || possibleX > 7 ||
            possibleY < 0 || possibleY > 7 ) {
                continue;
            }
            
            if(board.getTiles()[possibleX][possibleY].getPiece() == null) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(possibleX, possibleY)));
            } else if (board.getTiles()[possibleX][possibleY].getPiece().getAlliance() != this.getAlliance()) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(possibleX, possibleY)));
            } 
        }
        return list;
    }

}
