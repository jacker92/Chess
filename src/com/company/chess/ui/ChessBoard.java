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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ChessBoard extends BorderPane {

    public Space[][] spaces = new Space[8][8];
    private Board board;
    private boolean selected;
    private Position start;
    private Game game;
    private Label turnLabel;
    private Label statusLabel;
    private HBox box;
    private GridPane pane;
    

    public ChessBoard() {

        this.pane = new GridPane();
        this.turnLabel = new Label("Turn: White");
        this.statusLabel = new Label("Select piece to be moved");
        this.box = new HBox();
        
        board = new Board();
        this.game = new Game(board);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {

                boolean light = ((x + y) % 2 != 0); // checkerboard space colors
                spaces[x][y] = new Space(light, x, y);

                pane.add(spaces[x][y], x, y);

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
            statusLabel.setText("selected " + spaces[xVal][yVal].getPiece().getName());
            this.start = new Position(xVal, yVal);
            this.selected = true;
        } else {
            Move move = new Move(spaces[start.getX()][start.getY()].getPiece(), start, new Position(xVal, yVal));
            Piece piece = spaces[start.getX()][start.getY()].getPiece();
            if (piece.isLegalMove(move, board)) {
                if ((game.isWhiteTurn() && piece.getAlliance() == Alliance.WHITE) || (!game.isWhiteTurn() && piece.getAlliance() == Alliance.BLACK)) {
                  
                  
                    if(game.movePiece(move)) {
                        statusLabel.setText("Select piece to be moved");
                        movePieceOnScreen(move);
                        
                        // Change label
                        if(game.isWhiteTurn()) {
                            turnLabel.setText("Turn: White");
                        } else {
                            turnLabel.setText("Turn: Black");
                        }
                    } 
                    checkForCheckedAndMateOptions();
                }   
            } 
             this.selected = false;
             
        }
    }

    private void checkForCheckedAndMateOptions() {
        if(board.IsChecked(Alliance.BLACK)) {
            statusLabel.setText("Black is in check");
            if(board.IsCheckMated(Alliance.BLACK)) {
                new Alert(Alert.AlertType.INFORMATION, "Black is checkmated!").showAndWait();
                System.exit(0);
            }
            
        } else if (board.IsChecked(Alliance.WHITE)) {
            statusLabel.setText("White is in check");
            if (board.IsCheckMated(Alliance.WHITE)) {
                new Alert(Alert.AlertType.INFORMATION, "White is checkmated!").showAndWait();
                System.exit(0);
            }
        }
    }

    public void onStart() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                spaces[x][y].setPiece(board.getTiles()[x][y].getPiece());
            }
        }
        
        // Add Labels and gridpane to root
        this.setCenter(pane);
        box.getChildren().add(turnLabel);
        box.getChildren().add(statusLabel);
        box.setSpacing(20);
        
        this.setTop(box);
    }

 
    private void movePieceOnScreen(Move move) {
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
