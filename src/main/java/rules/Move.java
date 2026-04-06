package rules;

import pieces.Piece;
import state.Board;
import state.Square;
import util.Color;

/**
 * Represents a chess move from one square to another.
 * Encapsulates the source square, target square, and the piece being moved.
 */
public class Move {
    private final Square current, target;
    private final Piece piece;
    
    /**
     * Constructs a Move from the current square to the target square.
     * Captures the piece at the current square as the moving piece.
     * 
     * @param current The square the piece moves from
     * @param target The square the piece moves to
     */
    public Move(Square current, Square target) {
        this.target = target;
        this.current = current;
        this.piece = current.getPiece();
    }

    /**
     * Gets the target square of this move.
     * 
     * @return The square the piece is moving to
     */
    public Square getTarget() {
        return target;
    }

    /**
     * Gets the current (source) square of this move.
     * 
     * @return The square the piece is moving from
     */
    public Square getCurrent() {
        return current;
    }

    /**
     * Gets the piece being moved.
     * 
     * @return The piece that will be moved
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Executes this move on the board.
     * Removes the piece from the current square and places it on the target square.
     */
    public void execute() {
        current.setPiece(null);
        target.setPiece(piece);
    }

    /**
     * Creates a copy of this move on a different board.
     * Useful for move validation without modifying the actual board.
     * 
     * @param boardCopy The board on which to create the move copy
     * @return A new Move object on the copied board
     */
    public Move copy(Board boardCopy) {
        var currentCopy = boardCopy.getSquare(current.getRank(), current.getFile());
        var targetCopy = boardCopy.getSquare(target.getRank(), target.getFile());
        return new Move(currentCopy, targetCopy);
    }

    /**
     * Gets the color of the piece being moved.
     * 
     * @return The color (WHITE or BLACK) of the moving piece
     */
    public Color getColor() {
        return current.getPiece().getColor();
    }

    /**
     * Returns the algebraic notation representation of this move.
     * Format: piece symbol (if not a pawn) + target square
     * 
     * @return The algebraic notation of this move
     */
    @Override
    public String toString() {
        var moveString = new StringBuilder();
        if(piece instanceof pieces.Pawn) {
            moveString.append("");
        } else {
            moveString.append(piece.toString());
        }
        moveString.append(target.toString());
        return moveString.toString();
    }
}
