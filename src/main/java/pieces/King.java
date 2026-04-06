package pieces;

import java.util.ArrayList;

import movesets.DiagonalMoveset;
import movesets.HorizontalMoveset;
import movesets.VerticalMoveset;
import state.Board;
import state.Square;
import util.Color;

/**
 * Represents a king chess piece.
 * A king can move one square in any direction (horizontal, vertical, or diagonal).
 */
public class King extends Piece {
    /**
     * Constructs a King with a specified color.
     * 
     * @param color The color of this king (WHITE or BLACK)
     */
    public King(Color color) {
        super(color);
    }

    /**
     * Gets the point value of a king.
     * 
     * @return Integer.MAX_VALUE, representing the infinite value of the king
     */
    @Override
    public int getValue() {
        return Integer.MAX_VALUE;
    }

    /**
     * Gets all possible moves for this king.
     * Includes all adjacent squares in all directions (limited to 1 square movement).
     * 
     * @param board The board state to determine possible moves
     * @return A list of squares the king can move to
     */
    @Override
    public ArrayList<Square> possibleMoves(Board board) {
        var moves = new ArrayList<Square>();
        moves.addAll(new DiagonalMoveset(board, 1).getPossibleMoves(this));
        moves.addAll(new HorizontalMoveset(board, 1).getPossibleMoves(this));
        moves.addAll(new VerticalMoveset(board, 1).getPossibleMoves(this));
        return moves;
    }
    
    /**
     * Returns the string representation of this king.
     * 
     * @return "K" for white kings, "k" for black kings
     */
    @Override
    public String toString() {
        return color == Color.WHITE ? "K" : "k";
    }

    /**
     * Creates a copy of this king.
     * 
     * @return A new King object with the same color
     */
    @Override
    public Piece copy() {
        return new King(this.color);
    }
}
