package com.company.chess.board;

import com.company.chess.initializer.GameInitializer;
import com.company.chess.pieces.Alliance;
import com.company.chess.pieces.Piece;
import com.company.chess.pieces.PieceName;
import com.company.chess.pieces.Position;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class Board {

    Tile[][] tiles;

    public Board() {
        tiles = createBoard();
        tiles = GameInitializer.addPieces(tiles);
    }

    public List<Piece> getAllPieces() {
        return getTilesAsList().stream().filter(n -> n.getPiece() == null)
                .map(n -> n.getPiece()).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Tile> getTilesAsList() {
        List<Tile> tilesAsList = new ArrayList<>();
        for (Tile[] tile : tiles) {
            for (Tile tile1 : tile) {
                tilesAsList.add(tile1);
            }
        }
        return tilesAsList;
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
                    return piece.getPosition();
                }
            }
        }
        throw new RuntimeException(alliance + " KING WAS NOT FOUND");
    }

    public boolean IsChecked(Alliance alliance) {

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
                    if (move.getEndPosition().equals(getKingPosition(alliance))) {
                        return true;
                    }
                }
            }
        }
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
                    if (move.executeMove(this, whiteTurn)) {
                        return false;
                    }
                }
            }

        }
        return true;
    }
}
