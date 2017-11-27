package com.company.chess.pieces;

import com.company.chess.board.Board;
import com.company.chess.board.Move;
import java.util.List;
import javafx.scene.image.Image;

public abstract class Piece {

    protected Position position;
    protected Alliance alliance;
    protected PieceName name;
    protected Board board;
    protected Image image;
    protected boolean moved;

    public Piece(Position position, Alliance alliance, PieceName name) {
        this.position = position;
        this.alliance = alliance;
        this.name = name;
        String url = "com/company/chess/pieces/assets/"
                + this.alliance.toString().toLowerCase() + "_" + name.toString().toLowerCase() + ".png";
        this.image = new Image(url);
        this.moved = false;
    }

    public Position getPosition() {
        return position;
    }

    public PieceName getName() {
        return name;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard() {
        this.board = board;
    }

    public void setName(PieceName name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public abstract List<Move> showAllAvailableMoves(Board board);

    public boolean isLegalMove(Move move, Board board) {
        for (Move xmove : showAllAvailableMoves(board)) {
            if (xmove.equals(move)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSpecialMove(Move move, Board board) {
        for(Move xmove : showAllAvailableMoves(board)) {
            if (xmove.equals(move) && xmove.isSpecialMove()) return true;   
        }
    return false;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

}
