package pieces;

import java.util.ArrayList;

import movesets.DiagonalMoveset;
import state.Board;
import state.Square;
import util.Color;

/**
 * Represents a bishop chess piece.
 * A bishop can move any number of squares diagonally.
 */
public class Bishop extends Piece {
    /**
     * Constructs a Bishop with a specified color.
     * 
     * @param color The color of this bishop (WHITE or BLACK)
     */
    public Bishop(Color color) {
        super(color);
    }

    /**
     * Gets the point value of a bishop.
     * 
     * @return 3, the standard point value of a bishop
     */
    @Override
    public int getValue() {
        return 3;
    }

    /**
     * Gets all possible moves for this bishop.
     * Includes all diagonal moves.
     * 
     * @param board The board state to determine possible moves
     * @return A list of squares the bishop can move to
     */
    @Override
    public ArrayList<Square> possibleMoves(Board board) {
        return new DiagonalMoveset(board, board.getSize()).getPossibleMoves(this);
    }

    /**
     * Returns the string representation of this bishop.
     * 
     * @return "B" for white bishops, "b" for black bishops
     */
    @Override
    public String toString() {
        return color == Color.WHITE ? "B" : "b";
    }

    /**
     * Creates a copy of this bishop.
     * 
     * @return A new Bishop object with the same color
     */
    @Override
    public Piece copy() {
        return new Bishop(this.color);
    }
}
