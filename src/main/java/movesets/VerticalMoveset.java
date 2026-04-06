package movesets;

import java.util.ArrayList;

import pieces.Piece;
import state.Board;
import state.Square;

/**
 * Defines vertical movement patterns for chess pieces.
 * Handles up and down movement with optional range limiting.
 */
public class VerticalMoveset extends Moveset {
    private final int maxSquares;
    
    /**
     * Constructs a VerticalMoveset with a maximum range.
     * 
     * @param board The board on which moves will be calculated
     * @param maxSquares The maximum number of squares to move vertically
     */
    public VerticalMoveset(Board board, int maxSquares) {
        super(board);
        this.maxSquares = maxSquares;
    }

    /**
     * Gets all possible vertical moves for a piece.
     * 
     * @param piece The piece to get vertical moves for
     * @return A list of squares the piece can move to vertically
     */
    @Override
    public ArrayList<Square> getPossibleMoves(Piece piece) {
        var moves = new ArrayList<Square>();
        var square = board.getSquare(piece.getRank(), piece.getFile());
        moves.addAll(VerticallyUp(square, board));
        moves.addAll(VerticallyDown(square, board));
        return moves;
    }

    /**
     * Gets vertical moves in the up direction.
     * 
     * @param square The square the piece is on
     * @param board The board to calculate moves on
     * @return A list of squares the piece can move to upward
     */
    private ArrayList<Square> VerticallyUp(Square square, Board board) {
        var moves = new ArrayList<Square>();
        var limit = Math.min(square.getRank() + maxSquares, board.getSize() - 1);
        for (int r = square.getRank() + 1; r <= limit; r++) {
            var nextSquare = board.getSquare(r, square.getFile());
            var piece = nextSquare.getPiece();
            if (!nextSquare.hasPiece() || canCapture(square, nextSquare)) {
                moves.add(nextSquare);
            }
            if (piece != null) {
                break;
            }
        }
        return moves;
    }

    /**
     * Gets vertical moves in the down direction.
     * 
     * @param square The square the piece is on
     * @param board The board to calculate moves on
     * @return A list of squares the piece can move to downward
     */
    private ArrayList<Square> VerticallyDown(Square square, Board board) {
        var moves = new ArrayList<Square>();
        var limit = Math.max(square.getRank() - maxSquares, 0);
        for (int r = square.getRank() - 1; r >= limit; r--) {
            var nextSquare = board.getSquare(r, square.getFile());
            var piece = nextSquare.getPiece();
            if (!nextSquare.hasPiece() || canCapture(square, nextSquare)) {
                moves.add(nextSquare);
            }
            if (piece != null) {
                break;
            }
        }
        return moves;
    }
}
