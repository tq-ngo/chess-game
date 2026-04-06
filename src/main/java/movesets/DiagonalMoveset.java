package movesets;

import java.util.ArrayList;

import pieces.Piece;
import state.Board;
import state.Square;

/**
 * Defines diagonal movement patterns for chess pieces.
 * Handles movement in all four diagonal directions with optional range limiting.
 */
public class DiagonalMoveset extends Moveset {
    int maxSquares;
    
    /**
     * Constructs a DiagonalMoveset with a maximum range.
     * 
     * @param board The board on which moves will be calculated
     * @param maxSquares The maximum number of squares to move diagonally
     */
    public DiagonalMoveset(Board board, int maxSquares) {
        super(board);
        this.maxSquares = maxSquares;
    }

    /**
     * Gets all possible diagonal moves for a piece.
     * 
     * @param piece The piece to get diagonal moves for
     * @return A list of squares the piece can move to diagonally
     */
    @Override
    public ArrayList<Square> getPossibleMoves(Piece piece) {
        var moves = new ArrayList<Square>();
        moves.addAll(getUpRightMoves(piece));
        moves.addAll(getUpLeftMoves(piece));
        moves.addAll(getDownRightMoves(piece));
        moves.addAll(getDownLeftMoves(piece));
        return moves;
    }

    /**
     * Gets diagonal moves in the up-right direction.
     * 
     * @param piece The piece to get moves for
     * @return A list of squares the piece can move to up-right
     */
    private ArrayList<Square> getUpRightMoves(Piece piece) {
        var moves = new ArrayList<Square>();
        var square = board.getSquare(piece);
        var limit = Math.min(square.getRank() + maxSquares, board.getSize() - 1);

        for (int r = square.getRank() + 1, f = square.getFile() + 1; r <= limit && f < board.getSize(); r++, f++) {
            var nextSquare = board.getSquare(r, f);
            var nextPiece = nextSquare.getPiece();
            if (!nextSquare.hasPiece() || canCapture(square, nextSquare)) {
                moves.add(nextSquare);
            }
            if (nextPiece != null) {
                break;
            }
        }

        return moves;
    }

    /**
     * Gets diagonal moves in the up-left direction.
     * 
     * @param piece The piece to get moves for
     * @return A list of squares the piece can move to up-left
     */
    private ArrayList<Square> getUpLeftMoves(Piece piece) {
        var moves = new ArrayList<Square>();
        var square = board.getSquare(piece);
        var limit = Math.min(square.getRank() + maxSquares, board.getSize() - 1);

        for (int r = square.getRank() + 1, f = square.getFile() - 1; r <= limit && f >= 0; r++, f--) {
            var nextSquare = board.getSquare(r, f);
            var nextPiece = nextSquare.getPiece();
            if (!nextSquare.hasPiece() || canCapture(square, nextSquare)) {
                moves.add(nextSquare);
            }
            if (nextPiece != null) {
                break;
            }
        }

        return moves;
    }

    /**
     * Gets diagonal moves in the down-right direction.
     * 
     * @param piece The piece to get moves for
     * @return A list of squares the piece can move to down-right
     */
    private ArrayList<Square> getDownRightMoves(Piece piece) {
        var moves = new ArrayList<Square>();
        var square = board.getSquare(piece);
        var limit = Math.max(square.getRank() - maxSquares, 0);

        for (int r = square.getRank() - 1, f = square.getFile() + 1; r >= limit && f < board.getSize(); r--, f++) {
            var nextSquare = board.getSquare(r, f);
            var nextPiece = nextSquare.getPiece();
            if (!nextSquare.hasPiece() || canCapture(square, nextSquare)) {
                moves.add(nextSquare);
            }
            if (nextPiece != null) {
                break;
            }
        }

        return moves;
    }

    /**
     * Gets diagonal moves in the down-left direction.
     * 
     * @param piece The piece to get moves for
     * @return A list of squares the piece can move to down-left
     */
    private ArrayList<Square> getDownLeftMoves(Piece piece) {
        var moves = new ArrayList<Square>();
        var square = board.getSquare(piece);
        var limit = Math.max(square.getRank() - maxSquares, 0);
        
        for (int r = square.getRank() - 1, f = square.getFile() - 1; r >= limit && f >= 0; r--, f--) {
            var nextSquare = board.getSquare(r, f);
            var nextPiece = nextSquare.getPiece();
            if (!nextSquare.hasPiece() || canCapture(square, nextSquare)) {
                moves.add(nextSquare);
            }
            if (nextPiece != null) {
                break;
            }
        }

        return moves;
    }

}
