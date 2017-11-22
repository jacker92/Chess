package com.company.chess.pieces;

import com.company.chess.board.Board;
import com.company.chess.board.Move;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Queen extends Piece {

    public Queen(int x, int y, Alliance alliance) {
        super(new Position(x, y), alliance, PieceName.QUEEN);
    }

    @Override
    public List<Move> showAllAvailableMoves(Board board) {
        List<Move> list = new ArrayList<>();

        int currentX = this.position.getX();
        int currentY = this.position.getY();

        // Let's use bishop and rook's code
        // Let's create puppet bishop
        Bishop bishop = new Bishop(currentX, currentY, this.getAlliance());
        // Let's create puppet rook
        Rook rook = new Rook(currentX, currentY, this.getAlliance());

        list = bishop.showAllAvailableMoves(board);
        list.addAll(rook.showAllAvailableMoves(board));
        
        // Change the piece to Queen
        list = list.stream().map(n -> n.setPiece(this)).collect(Collectors.toCollection(ArrayList::new));
        return list;
    }

}
