package com.company.chess.pieces;

import com.company.chess.board.Board;
import com.company.chess.board.Move;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(int x, int y, Alliance alliance) {
        super(new Position(x, y), alliance, PieceName.ROOK);
    }

    public List<Move> showAllAvailableMoves(Board board) {
        List<Move> list = new ArrayList<>();

        int currentX = this.position.getX();
        int currentY = this.position.getY();

        list = showMovesToLeft(currentX, board, currentY, list);
        list = showMovesToRight(currentX, board, currentY, list);
        list = showMovesToUp(currentX, board, currentY, list);
        list = showMovesToDown(currentX, board, currentY, list);
        return list;
    }

    private List<Move> showMovesToDown(int currentX, Board board, int currentY, List<Move> list) {
        for (int i = currentY; i <= 7; i++) {
            if (currentY == i) {
                continue;
            }

            if (board.getTiles()[currentX][i].getPiece() == null) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX, i)));
                continue;
            } else if (board.getTiles()[currentX][i].getPiece().getAlliance() != this.getAlliance()) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX, i)));
                break;
            } else {
                break;
            }
        }
        return list;
    }

    private List<Move> showMovesToUp(int currentX, Board board, int currentY, List<Move> list) {
        for (int i = currentY; i >= 0; i--) {
            if (currentY == i) {
                continue;
            }

            if (board.getTiles()[currentX][i].getPiece() == null) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX, i)));
                continue;
            } else if (board.getTiles()[currentX][i].getPiece().getAlliance() != this.getAlliance()) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(currentX, i)));
                break;
            } else {
                break;
            }
        }

        return list;
    }

    private List<Move> showMovesToRight(int currentX, Board board, int currentY, List<Move> list) {
        for (int i = currentX; i <= 7; i++) {
            if (currentX == i) {
                continue;
            }

            if (board.getTiles()[i][currentY].getPiece() == null) {
                list.add(new Move(this,new Position(currentX, currentY), new Position(i, currentY)));
                continue;
            } else if (board.getTiles()[i][currentY].getPiece().getAlliance() != this.getAlliance()) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(i, currentY)));
                break;
            } else {
                break;
            }
        }
        return list;
    }

    private List<Move> showMovesToLeft(int currentX, Board board, int currentY, List<Move> list) {
        for (int i = currentX; i >= 0; i--) {
            if (currentX == i) {
                continue;
            }

            if (board.getTiles()[i][currentY].getPiece() == null) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(i, currentY)));
                continue;
            } else if (board.getTiles()[i][currentY].getPiece().getAlliance() != this.getAlliance()) {
                list.add(new Move(this, new Position(currentX, currentY), new Position(i, currentY)));
                break;
            } else {
                break;
            }
        }

        return list;
    }
    
    

}
