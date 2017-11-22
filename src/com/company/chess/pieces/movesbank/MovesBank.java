package com.company.chess.pieces.movesbank;

import com.company.chess.pieces.Position;

public class MovesBank {
    
    public static final Position[] knightPositions = {new Position(1,-2), new Position(2,-1),
    new Position(2,1), new Position(1,2), new Position(-1,2), new Position(-2,1),
    new Position(-2,-1), new Position(-1,-2)};

    public static final Position[] kingPositions = {new Position(1,0), 
        new Position(1,1), new Position (0,1), new Position(-1, 1), new Position(-1,0),
        new Position(-1,-1), new Position(0,-1), new Position(1,-1)};
   
 
}
