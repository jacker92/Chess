package com.company.chess.pieces;

import com.company.chess.board.Board;
import com.company.chess.board.Move;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(int x, int y, Alliance alliance) {
        super(new Position(x, y), alliance, PieceName.PAWN);
    }

    @Override
    public List<Move> showAllAvailableMoves(Board board) {
        List<Move> list = new ArrayList<>();

        int currentX = this.position.getX();
        int currentY = this.position.getY();

        if (this.getAlliance() == Alliance.WHITE) {
            list = whiteMove(board, currentX, currentY, list);
        } else {
            list.addAll(blackMove(board, currentX, currentY, list));
        }
        return list;
    }

    private List<Move> blackMove(Board board, int currentX, int currentY, List<Move> list) {
        if (board.getTiles()[currentX][currentY + 1].getPiece() == null) {
            list.add(new Move(this, new Position(currentX, currentY), new Position(currentX, currentY + 1)));
            if (!moved) {
                if (board.getTiles()[currentX][currentY + 2].getPiece() == null) {
                    list.add(new Move(this, new Position(currentX, currentY), new Position(currentX, currentY + 2)));
                }
            }
        }

        // Without this, first pawn will throw exception
        if (currentX > 0) {
            if (board.getTiles()[currentX - 1][currentY + 1].getPiece() != null) {
                if (board.getTiles()[currentX - 1][currentY + 1].getPiece().getAlliance() == Alliance.WHITE) {
                    list.add(new Move(this, new Position(currentX, currentY), new Position(currentX - 1, currentY + 1)));
                }
            }
        }

        // Without this, last pawn will throw exception
        if (currentX < 7) {
            if (board.getTiles()[currentX + 1][currentY + 1].getPiece() != null) {
                if (board.getTiles()[currentX + 1][currentY + 1].getPiece().getAlliance() == Alliance.WHITE) {
                    list.add(new Move(this, new Position(currentX, currentY), new Position(currentX + 1, currentY + 1)));
                }
            }
        }

        return list;
    }

    private List<Move> whiteMove(Board board, int currentX, int currentY, List<Move> list) {

        if (board.getTiles()[currentX][currentY - 1].getPiece() == null) {
            list.add(new Move(this, new Position(currentX, currentY), new Position(currentX, currentY - 1)));
            if (!moved) {
                if (board.getTiles()[currentX][currentY - 2].getPiece() == null) {
                    list.add(new Move(this, new Position(currentX, currentY), new Position(currentX, currentY - 2)));
                }
            }
        }
        // Without this, first pawn will throw exception
        if (currentX > 0) {
            if (board.getTiles()[currentX - 1][currentY - 1].getPiece() != null) {
                if (board.getTiles()[currentX - 1][currentY - 1].getPiece().getAlliance() == Alliance.BLACK) {
                    list.add(new Move(this, new Position(currentX, currentY), new Position(currentX - 1, currentY - 1)));
                }
            }
        }

        // Without this, last pawn will throw exception
        if (currentX < 7) {
            if (board.getTiles()[currentX + 1][currentY - 1].getPiece() != null) {
                if (board.getTiles()[currentX + 1][currentY - 1].getPiece().getAlliance() == Alliance.BLACK) {
                    list.add(new Move(this, new Position(currentX, currentY), new Position(currentX + 1, currentY - 1)));
                }
            }
        }

        return list;
    }

}
