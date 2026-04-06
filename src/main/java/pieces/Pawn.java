package pieces;

import java.util.ArrayList;

import movesets.PawnMoveset;
import state.Board;
import state.Square;
import util.Color;

/**
 * Represents a pawn chess piece.
 * A pawn can move forward one or two squares, and capture diagonally.
 */
public class Pawn extends Piece {
    /**
     * Constructs a Pawn with a specified color.
     * 
     * @param color The color of this pawn (WHITE or BLACK)
     */
    public Pawn(Color color) {
        super(color);
    }

    /**
     * Gets the point value of a pawn.
     * 
     * @return 1, the standard point value of a pawn
     */
    @Override
    public int getValue() {
        return 1;
    }

    /**
     * Gets all possible moves for this pawn.
     * Includes forward moves, double moves from starting position, and capture moves.
     * 
     * @param board The board state to determine possible moves
     * @return A list of squares the pawn can move to
     */
    @Override
    public ArrayList<Square> possibleMoves(Board board) {
        return new PawnMoveset(board).getPossibleMoves(this);
    }

    /**
     * Returns the string representation of this pawn.
     * 
     * @return "P" for white pawns, "p" for black pawns
     */
    @Override
    public String toString() {
        return color == Color.WHITE ? "P" : "p";
    }

    /**
     * Creates a copy of this pawn.
     * 
     * @return A new Pawn object with the same color
     */
    @Override
    public Piece copy() {
        return new Pawn(this.color);
    }
}
