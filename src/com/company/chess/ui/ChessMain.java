package com.company.chess.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChessMain extends Application {
  
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new ChessBoard();

        Scene scene = new Scene(root, 400, 420);
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
