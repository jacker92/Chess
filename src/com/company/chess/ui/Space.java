package com.company.chess.ui;

import com.company.chess.pieces.Piece;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Space extends Button{

    private final int x;
    private final int y;
    private Piece piece;

    public Space(boolean light, int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.piece = null;
        this.getStyleClass().add("chess-space");
        
        if (light) {
            this.getStyleClass().add("chess-space-light");
        } else {
            this.getStyleClass().add("chess-space-dark");
        }
        
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        
        if(this.piece != null) {
            this.setGraphic(new ImageView(piece.getImage()));
        } else {
            this.setGraphic(new ImageView());
        }
    }
    
    public Piece getPiece() {
        return piece;
    }
  
    
}
