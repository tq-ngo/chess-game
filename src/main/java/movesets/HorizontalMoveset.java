package movesets;

import java.util.ArrayList;

import pieces.Piece;
import state.Board;
import state.Square;

/**
 * Defines horizontal movement patterns for chess pieces.
 * Handles left and right movement with optional range limiting.
 */
public class HorizontalMoveset extends Moveset {
    private final int maxSquares;
    
    /**
     * Constructs a HorizontalMoveset with a maximum range.
     * 
     * @param board The board on which moves will be calculated
     * @param maxSquares The maximum number of squares to move horizontally
     */
    public HorizontalMoveset(Board board, int maxSquares) {
        super(board);
        this.maxSquares = maxSquares;
    }

    /**
     * Gets all possible horizontal moves for a piece.
     * 
     * @param piece The piece to get horizontal moves for
     * @return A list of squares the piece can move to horizontally
     */
    @Override
    public ArrayList<Square> getPossibleMoves(Piece piece) {
        var moves = new ArrayList<Square>();
        var square = board.getSquare(piece.getRank(), piece.getFile());
        moves.addAll(HorizontallyRight(square, board));
        moves.addAll(HorizontallyLeft(square, board));
        return moves;
    }

    /**
     * Gets horizontal moves in the right direction.
     * 
     * @param square The square the piece is on
     * @param board The board to calculate moves on
     * @return A list of squares the piece can move to on the right
     */
    private ArrayList<Square> HorizontallyRight(Square square, Board board) {
        var moves = new ArrayList<Square>();
        var limit = Math.min(square.getFile() + maxSquares, board.getSize() - 1);

        for (int f = square.getFile() + 1; f <= limit; f++) {
            var nextSquare = board.getSquare(square.getRank(), f);
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
     * Gets horizontal moves in the left direction.
     * 
     * @param square The square the piece is on
     * @param board The board to calculate moves on
     * @return A list of squares the piece can move to on the left
     */
    private ArrayList<Square> HorizontallyLeft(Square square, Board board) {
        var moves = new ArrayList<Square>();
        var limit = Math.max(square.getFile() - maxSquares, 0);

        for (int f = square.getFile() - 1; f >= limit; f--) {
            var nextSquare = board.getSquare(square.getRank(), f);
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
