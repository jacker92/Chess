package com.company.chess.ui;

import com.company.chess.board.Board;
import com.company.chess.board.Move;
import com.company.chess.game.Game;
import com.company.chess.pieces.Alliance;
import com.company.chess.pieces.Piece;
import com.company.chess.pieces.Position;
import com.company.chess.pieces.Queen;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    public Button newGameButton = new Button();

    public ChessBoard() {

        this.pane = new GridPane();
        this.turnLabel = new Label("Turn: White");
        this.statusLabel = new Label("Select piece to be moved");
        this.box = new HBox();
        this.newGameButton = new Button("New Game");

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
            // Set red background to selected tile
            spaces[xVal][yVal].getStyleClass().add("chess-space-active");
        } else {
            // Remove red background
            spaces[start.getX()][start.getY()].getStyleClass().remove("chess-space-active");
            handleMoving(xVal, yVal);
        }
    }

    private void checkForCheckedAndMateOptions() {
        if (board.IsChecked(Alliance.BLACK)) {
            statusLabel.setText("Black is in check");
            if (board.IsCheckMated(Alliance.BLACK)) {
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
        box.getChildren().add(newGameButton);
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);
        this.setTop(box);
    }

    private void movePieceOnScreen(Move move) {
        // Check if move is special move
        System.out.println("on move piece on screen");
        System.out.println(move);
        if (move.isSpecialMove()) {
            moveSpecialMoveOnScreen(move);
        }

        // Remove piece from original position
        spaces[move.getStartPosition().getX()][move.getStartPosition().getY()].setPiece(null);
        // Set piece to new location
        spaces[move.getEndPosition().getX()][move.getEndPosition().getY()].setPiece(move.getPiece());
        // Let's change piece's location information
        spaces[move.getEndPosition().getX()][move.getEndPosition().getY()].getPiece()
                .setPosition(new Position(move.getEndPosition().getX(), move.getEndPosition().getY()));

        // Set the piece that it is moved
        Piece piece = spaces[move.getEndPosition().getX()][move.getEndPosition().getY()].getPiece();
        piece.setMoved(true);
        spaces[move.getEndPosition().getX()][move.getEndPosition().getY()].setPiece(piece);
    }

    private void handleMoving(int xVal, int yVal) {

        Move move = new Move(spaces[start.getX()][start.getY()].getPiece(), start, new Position(xVal, yVal));
        Piece piece = spaces[start.getX()][start.getY()].getPiece();
        System.out.println(move);
        if (piece.isLegalMove(move, board)) {
            // If it is special move
            if (piece.isSpecialMove(move, board)) {
                move.setSpecialMove(true);
            }
            if ((game.isWhiteTurn() && piece.getAlliance() == Alliance.WHITE) || (!game.isWhiteTurn() && piece.getAlliance() == Alliance.BLACK)) {
                if (game.movePiece(move, false)) {
                    statusLabel.setText("Select piece to be moved");

                    movePieceOnScreen(move);

                    // Change label
                    if (game.isWhiteTurn()) {
                        turnLabel.setText("Turn: White");
                    } else {
                        turnLabel.setText("Turn: Black");
                    }
                    checkPawnPromotion();
                }
                checkForCheckedAndMateOptions();
            }
        }
        this.selected = false;

    }

    private void checkPawnPromotion() {
        // Check if pawn is at end at top or bottom, then promote it to queen
        if (game.isPawnIsPromotionable()) {
            int x = game.getPawnPosition().getX();
            int y = game.getPawnPosition().getY();
            spaces[x][y].setPiece(new Queen(x, y, game.getPawnAlliance()));
            game.setPawnIsPromotionable(false);
        }
    }

    private void moveSpecialMoveOnScreen(Move move) {
        // Handle castling moves
        // Long castle
        if (move.getEndPosition().getX() == 2) {
            Piece piece = spaces[0][move.getStartPosition().getY()].getPiece();
            // Set piece to new location
            spaces[3][move.getEndPosition().getY()].setPiece(piece);
            // Let's change piece's location information
            spaces[3][move.getEndPosition().getY()].getPiece()
                    .setPosition(new Position(3, move.getEndPosition().getY()));
            // Remove piece from original position
            spaces[0][move.getStartPosition().getY()].setPiece(null);

            // short castle
        } else if (move.getEndPosition().getX() == 6) {
            Piece piece = spaces[7][move.getStartPosition().getY()].getPiece();
            // Set piece to new location
            spaces[5][move.getEndPosition().getY()].setPiece(piece);
            // Let's change piece's location information
            spaces[5][move.getEndPosition().getY()].getPiece()
                    .setPosition(new Position(5, move.getEndPosition().getY()));
            // Remove piece from original position
            spaces[7][move.getStartPosition().getY()].setPiece(null);
        }
    }
}
