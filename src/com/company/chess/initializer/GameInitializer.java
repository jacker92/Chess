package com.company.chess.initializer;

import com.company.chess.board.Tile;
import com.company.chess.pieces.*;

public class GameInitializer {

    public static Tile[][] addPieces(Tile[][] tiles) {

        // Rooks
        tiles[0][0].setPiece(new Rook(0, 0, Alliance.BLACK));
        tiles[7][0].setPiece(new Rook(7, 0, Alliance.BLACK));
        tiles[0][7].setPiece(new Rook(0, 7, Alliance.WHITE));
        tiles[7][7].setPiece(new Rook(7, 7, Alliance.WHITE));

        // Knights
        tiles[1][0].setPiece(new Knight(1, 0, Alliance.BLACK));
        tiles[6][0].setPiece(new Knight(6, 0, Alliance.BLACK));
        tiles[1][7].setPiece(new Knight(1, 7, Alliance.WHITE));
        tiles[6][7].setPiece(new Knight(6, 7, Alliance.WHITE));

        // Bishop
        tiles[2][0].setPiece(new Bishop(2, 0, Alliance.BLACK));
        tiles[5][0].setPiece(new Bishop(5, 0, Alliance.BLACK));
        tiles[2][7].setPiece(new Bishop(2, 7, Alliance.WHITE));
        tiles[5][7].setPiece(new Bishop(5, 7, Alliance.WHITE));

        // Queens
        tiles[3][0].setPiece(new Queen(3, 0, Alliance.BLACK));
        tiles[3][7].setPiece(new Queen(3, 7, Alliance.WHITE));

        // Kings
        tiles[4][0].setPiece(new King(4, 0, Alliance.BLACK));
        tiles[4][7].setPiece(new King(4, 7, Alliance.WHITE));

        // Pawns
        for (int i = 0; i < 8; i++) {
            tiles[i][1].setPiece(new Pawn(i, 1, Alliance.BLACK));
            tiles[i][6].setPiece(new Pawn(i, 6, Alliance.WHITE));
        }
        return tiles;
    }

}
