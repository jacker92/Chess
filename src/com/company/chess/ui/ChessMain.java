package com.company.chess.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChessMain extends Application {

    private ChessBoard board;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {

        this.board = new ChessBoard();
        BorderPane root = board;

        // This restarts application
        board.newGameButton.setOnAction(event -> {
            primaryStage.close();
            Platform.runLater(() -> new ChessMain().start(new Stage()));
        });

        scene = new Scene(root, 400, 420);
        scene.getStylesheets().add("com/company/chess/pieces/assets/stylesheet.css");

        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
