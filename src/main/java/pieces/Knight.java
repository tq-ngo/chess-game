package pieces;

import java.util.ArrayList;

import movesets.KnightMoveset;
import state.Board;
import state.Square;
import util.Color;

/**
 * Represents a knight chess piece.
 * A knight moves in an L-shape: two squares in one direction and one square perpendicular.
 */
public class Knight extends Piece {
    /**
     * Constructs a Knight with a specified color.
     * 
     * @param color The color of this knight (WHITE or BLACK)
     */
    public Knight(Color color) {
        super(color);
    }

    /**
     * Gets the point value of a knight.
     * 
     * @return 3, the standard point value of a knight
     */
    @Override
    public int getValue() {
        return 3;
    }

    /**
     * Gets all possible moves for this knight.
     * Includes all valid L-shaped moves.
     * 
     * @param board The board state to determine possible moves
     * @return A list of squares the knight can move to
     */
    @Override
    public ArrayList<Square> possibleMoves(Board board) {
        return new KnightMoveset(board).getPossibleMoves(this);
    }
    
    /**
     * Returns the string representation of this knight.
     * 
     * @return "N" for white knights, "n" for black knights
     */
    @Override
    public String toString() {
        return color == Color.WHITE ? "N" : "n";
    }

    /**
     * Creates a copy of this knight.
     * 
     * @return A new Knight object with the same color
     */
    @Override
    public Piece copy() {
        return new Knight(this.color);
    }
}
