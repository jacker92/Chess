package com.company.chess.board;

import com.company.chess.initializer.GameInitializer;
import com.company.chess.pieces.Alliance;
import com.company.chess.pieces.Piece;
import com.company.chess.pieces.PieceName;
import com.company.chess.pieces.Position;

public class Board {

    Tile[][] tiles;
    private boolean boardIsChecked;

    public Board() {
        tiles = createBoard();
        tiles = GameInitializer.addPieces(tiles);
    }

    private Tile[][] createBoard() {
        Tile returnableList[][] = new Tile[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                returnableList[x][y] = new Tile(new Position(x, y));
            }
        }
        return returnableList;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Position getKingPosition(Alliance alliance) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = tiles[x][y].getPiece();
                if (piece == null) {
                    continue;
                }
                if (piece.getName() == PieceName.KING && piece.getAlliance() == alliance) {
                    Position p = new Position(piece.getPosition().getX(), piece.getPosition().getY());
                    return p;
                }
            }
        }
        throw new RuntimeException(alliance + " KING WAS NOT FOUND");
    }

    public boolean IsChecked(Alliance alliance) {
        
        Position king = getKingPosition(alliance);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = tiles[x][y].getPiece();
                if (piece == null) {
                    continue;
                }
                if (piece.getAlliance() == alliance) {
                    continue;
                }
                for (Move move : piece.showAllAvailableMoves(this)) {
                    if (move.getEndPosition().equals(king)) {
                        boardIsChecked = true;
                        return true;
                    }
                }
            }
        }
        boardIsChecked = false;
        return false;
    }

    public boolean IsCheckMated(Alliance alliance) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = tiles[x][y].getPiece();
                if (piece == null) {
                    continue;
                }
                if (piece.getAlliance() != alliance) {
                    continue;
                }
                for (Move move : piece.showAllAvailableMoves(this)) {
                    boolean whiteTurn = true;
                    if(alliance == Alliance.BLACK) whiteTurn = false;
                    if (move.executeMove(this, whiteTurn, true)) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    public boolean isBoardIsChecked() {
        return boardIsChecked;
    }

    public void setBoardIsChecked(boolean boardIsChecked) {
        this.boardIsChecked = boardIsChecked;
    }
    
    
}
