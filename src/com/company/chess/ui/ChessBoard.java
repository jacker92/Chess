package com.company.chess.ui;

import com.company.chess.board.Board;
import com.company.chess.board.Move;
import com.company.chess.game.Game;
import com.company.chess.pieces.Alliance;
import com.company.chess.pieces.Pawn;
import com.company.chess.pieces.Piece;
import com.company.chess.pieces.PieceName;
import com.company.chess.pieces.Position;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

public class ChessBoard extends GridPane {

    public Space[][] spaces = new Space[8][8];
    private Board board;
    private boolean selected;
    private Position start;
    private Game game;

    public ChessBoard() {

        board = new Board();
        this.game = new Game(board);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {

                boolean light = ((x + y) % 2 != 0); // checkerboard space colors
                spaces[x][y] = new Space(light, x, y);

                this.add(spaces[x][y], x, y);

                // These have to be final for lambda expression
                final int xVal = x;
                final int yVal = y;

                spaces[x][y].setOnAction(n -> onSpaceClick(xVal, yVal));
            }
        }

        this.selected = false;
        onStart();
    }

    private void onSpaceClick(int xVal, int yVal) {
        if (!selected) {
            if (spaces[xVal][yVal].getPiece() == null) {
                return;
            }
            System.out.println("selected " + spaces[xVal][yVal].getPiece().getName());
            this.start = new Position(xVal, yVal);
            System.out.println(start);
            System.out.println("Select destination");
            this.selected = true;
        } else {
            Move move = new Move(spaces[start.getX()][start.getY()].getPiece(), start, new Position(xVal, yVal));
            Piece piece = spaces[start.getX()][start.getY()].getPiece();
            if (piece.isLegalMove(move, board)) {
                if ((game.isWhiteTurn() && piece.getAlliance() == Alliance.WHITE) || (!game.isWhiteTurn() && piece.getAlliance() == Alliance.BLACK)) {
                    System.out.println("It is legal move.");
                    if(board.blackIsChecked() || board.whiteIsChecked()) {
                        new Alert(Alert.AlertType.ERROR).showAndWait();
                    }
                     game.movePiece(move);
                     movePiece(move);
                     System.out.println("Piece moved.");
                } 
               
            } else {
                System.out.println("It is not legal move.");
            }
             this.selected = false;
        }
    }

    public void onStart() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                spaces[x][y].setPiece(board.getTiles()[x][y].getPiece());
            }
        }
    }

    private void movePiece(Move move) {
        // Remove piece from original position
        spaces[move.getStartPosition().getX()][move.getStartPosition().getY()].setPiece(null);
        // Set piece to new location
        spaces[move.getEndPosition().getX()][move.getEndPosition().getY()].setPiece(move.getPiece());
        // Let's change piece's location information
        spaces[move.getEndPosition().getX()][move.getEndPosition().getY()].getPiece()
        .setPosition(new Position(move.getEndPosition().getX(), move.getEndPosition().getY()));
        
        // If the piece is pawn, it has been moved, so have to set firstmoved to false
        if(spaces[move.getEndPosition().getX()][move.getEndPosition().getY()].getPiece().getName() == PieceName.PAWN) {
           Pawn pwn = (Pawn)spaces[move.getEndPosition().getX()][move.getEndPosition().getY()].getPiece();
           pwn.setMoved(true);
           spaces[move.getEndPosition().getX()][move.getEndPosition().getY()].setPiece(pwn);
        }
    }

}
