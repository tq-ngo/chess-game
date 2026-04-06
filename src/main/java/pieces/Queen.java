package pieces;

import java.util.ArrayList;

import movesets.DiagonalMoveset;
import movesets.HorizontalMoveset;
import movesets.VerticalMoveset;
import state.Board;
import state.Square;
import util.Color;

/**
 * Represents a queen chess piece.
 * A queen can move any number of squares horizontally, vertically, or diagonally.
 */
public class Queen extends Piece {
    /**
     * Constructs a Queen with a specified color.
     * 
     * @param color The color of this queen (WHITE or BLACK)
     */
    public Queen(Color color) {
        super(color);
    }

    /**
     * Gets the point value of a queen.
     * 
     * @return 9, the standard point value of a queen
     */
    @Override
    public int getValue() {
        return 9;
    }

    /**
     * Gets all possible moves for this queen.
     * Includes all horizontal, vertical, and diagonal moves.
     * 
     * @param board The board state to determine possible moves
     * @return A list of squares the queen can move to
     */
    @Override
    public ArrayList<Square> possibleMoves(Board board) {
        var moves = new ArrayList<Square>();
        moves.addAll(new DiagonalMoveset(board, board.getSize()).getPossibleMoves(this));
        moves.addAll(new HorizontalMoveset(board, board.getSize()).getPossibleMoves(this));
        moves.addAll(new VerticalMoveset(board, board.getSize()).getPossibleMoves(this));
        return moves;
    }
    
    /**
     * Returns the string representation of this queen.
     * 
     * @return "Q" for white queens, "q" for black queens
     */
    @Override
    public String toString() {
        return color == Color.WHITE ? "Q" : "q";
    }

    /**
     * Creates a copy of this queen.
     * 
     * @return A new Queen object with the same color
     */
    @Override
    public Piece copy() {
        return new Queen(this.color);
    }
}
