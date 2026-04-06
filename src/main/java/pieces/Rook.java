package pieces;

import java.util.ArrayList;

import movesets.HorizontalMoveset;
import movesets.VerticalMoveset;
import state.Board;
import state.Square;
import util.Color;

/**
 * Represents a rook chess piece.
 * A rook can move any number of squares horizontally or vertically.
 */
public class Rook extends Piece {
    /**
     * Constructs a Rook with a specified color.
     * 
     * @param color The color of this rook (WHITE or BLACK)
     */
    public Rook(Color color) {
        super(color);
    }

    /**
     * Gets the point value of a rook.
     * 
     * @return 5, the standard point value of a rook
     */
    @Override
    public int getValue() {
        return 5;
    }

    /**
     * Gets all possible moves for this rook.
     * Includes all horizontal and vertical moves.
     * 
     * @param board The board state to determine possible moves
     * @return A list of squares the rook can move to
     */
    @Override
    public ArrayList<Square> possibleMoves(Board board) {
        var moves = new ArrayList<Square>();
        moves.addAll(new HorizontalMoveset(board, board.getSize()).getPossibleMoves(this));
        moves.addAll(new VerticalMoveset(board, board.getSize()).getPossibleMoves(this));
        return moves;
    }

    /**
     * Returns the string representation of this rook.
     * 
     * @return "R" for white rooks, "r" for black rooks
     */
    @Override
    public String toString() {
        return color == Color.WHITE ? "R" : "r";
    }

    /**
     * Creates a copy of this rook.
     * 
     * @return A new Rook object with the same color
     */
    @Override
    public Piece copy() {
        return new Rook(this.color);
    }
}
