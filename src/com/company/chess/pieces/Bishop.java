package com.company.chess.pieces;

import com.company.chess.board.Board;
import com.company.chess.board.Move;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(int x, int y, Alliance alliance) {
        super(new Position(x, y), alliance, PieceName.BISHOP);
    }

    @Override
    public List<Move> showAllAvailableMoves(Board board) {
        List<Move> list = new ArrayList<>();

        int currentX = this.position.getX();
        int currentY = this.position.getY();

        list = showMovesToBottomLeft(currentX, board, currentY, list);
        list = showMovesToBottomRight(currentX, board, currentY, list);
        list = showMovesToTopLeft(currentX, board, currentY, list);
        list = showMovesToTopRight(currentX, board, currentY, list);

        return list;
        
    }

    private List<Move> showMovesToTopLeft(int currentX, Board board, int currentY, List<Move> list) {
        for (int i = 1; currentX-i >= 0; i++) {
            if(currentY-i < 0) {
                break;
            }
            if (board.getTiles()[currentX - i][currentY - i].getPiece() == null) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX - i, currentY - i)));
            } else if (board.getTiles()[currentX-i][currentY-i].getPiece().getAlliance() != this.getAlliance()) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX - i, currentY - i)));
                break;
            } else {
                break;
            }
        }
        return list;
    }

    private List<Move> showMovesToBottomRight(int currentX, Board board, int currentY, List<Move> list) {

        for (int i = 1; i + currentX < 8; i++) {
            if (currentY + i > 7) {
                break;
            }
            if (board.getTiles()[currentX + i][currentY + i].getPiece() == null) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX + i, currentY + i)));
            } else if (board.getTiles()[currentX + i][currentY + i].getPiece().getAlliance() != this.getAlliance()) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX + i, currentY + i)));
                break;
            } else {
                break;
            }
        }
        return list;
    }

    private List<Move> showMovesToBottomLeft(int currentX, Board board, int currentY, List<Move> list) {
        for (int i = 1; i+currentY < 8; i++) {
            if(currentX-i < 0) {
                break;
            }
            if (board.getTiles()[currentX-i][currentY+i].getPiece() == null) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX-i, currentY+i)));
            } else if (board.getTiles()[currentX-i][currentY+i].getPiece().getAlliance() != this.getAlliance()) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX-i, currentY+i)));
                break;
            } else {
                break;
            }
        }
        return list;
    }

    private List<Move> showMovesToTopRight(int currentX, Board board, int currentY, List<Move> list) {
            for (int i = 1; i + currentX < 8; i++) {
            if (currentY - i < 0) {
                break;
            }
            if (board.getTiles()[currentX + i][currentY - i].getPiece() == null) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX + i, currentY - i)));
            } else if (board.getTiles()[currentX + i][currentY - i].getPiece().getAlliance() != this.getAlliance()) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX + i, currentY - i)));
                break;
            } else {
                break;
            }
        }
        return list;
    }

}
