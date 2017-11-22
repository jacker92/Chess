package com.company.chess.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChessLayout extends Application {
  
    @Override
    public void start(Stage primaryStage) {
        
        
      GridPane root = new ChessBoard();

        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add("chesslayout/stylesheet.css");

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
