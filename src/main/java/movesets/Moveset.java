package movesets;

import java.util.ArrayList;

import pieces.Piece;
import state.Board;
import state.Square;

/**
 * Abstract base class for different movement patterns in chess.
 * Defines common functionality for generating possible moves for pieces.
 */
public abstract class Moveset {
    protected final Board board;
    
    /**
     * Constructs a Moveset with a specified board.
     * 
     * @param board The board on which moves will be calculated
     */
    public Moveset(Board board) {
        this.board = board;
    }

    /**
     * Gets all possible moves for a piece based on this moveset's rules.
     * 
     * @param piece The piece to get moves for
     * @return A list of squares the piece can move to according to this moveset
     */
    public abstract ArrayList<Square> getPossibleMoves(Piece piece);

    /**
     * Checks if a piece on one square can capture a piece on another square.
     * A capture is possible if the target square has an enemy piece.
     * 
     * @param from The square the attacking piece is on
     * @param to The square the target piece is on
     * @return true if the piece on 'from' can capture the piece on 'to', false otherwise
     */
    protected boolean canCapture(Square from, Square to) {
        return to.hasPiece() && to.getPiece().getColor() != from.getPiece().getColor();
    }
}
