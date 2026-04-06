package movesets;

import java.util.ArrayList;
import java.util.List;

import pieces.Piece;
import state.Board;
import state.Square;
import util.Color;

/**
 * Defines pawn movement patterns for chess pieces.
 * Handles forward movement, initial double moves, and diagonal captures.
 */
public class PawnMoveset extends Moveset {
    private final int left = -1;
    private final int right = 1;
    private final int singleMove = 1;
    private final int doubleMove = 2;
    private final int startingRankWhite = 1;
    private final int startingRankBlack;
    private final int moveDirectionWhite = 1;
    private final int moveDirectionBlack = -1;

    /**
     * Constructs a PawnMoveset.
     * Initializes starting rank for black pawns based on board size.
     * 
     * @param board The board on which moves will be calculated
     */
    public PawnMoveset(Board board) {
        super(board);
        startingRankBlack = board.getSize() - 2;
    }

    /**
     * Gets all possible pawn moves for a piece.
     * Includes forward moves, double moves from starting position, and capture moves.
     * 
     * @param piece The piece to get pawn moves for
     * @return A list of squares the piece can move to
     */
    @Override
    public ArrayList<Square> getPossibleMoves(Piece piece) {
        var moves = new ArrayList<Square>();
        
        moves.addAll(getForwardMove(piece, singleMove));
        if (!moves.isEmpty()) {
            if (!hasMoved(piece)) {
                moves.addAll(getForwardMove(piece, doubleMove));
            }
        }
        
        moves.addAll(getCaptureMoves(piece));
        return moves;
    }

    /**
     * Gets all capture moves (diagonal moves) available for a pawn.
     * 
     * @param piece The piece to get capture moves for
     * @return A list of squares the piece can capture on
     */
    private List<Square> getCaptureMoves(Piece piece) {
        var moves = new ArrayList<Square>();
        moves.addAll(getCaptureMove(piece, left));
        moves.addAll(getCaptureMove(piece, right));
        return moves;
    }

    /**
     * Gets the movement direction for a pawn based on its color.
     * White pawns move in the positive direction (upward), black pawns move negatively (downward).
     * 
     * @param piece The piece to get the direction for
     * @return 1 for white pawns, -1 for black pawns
     */
    private int getMoveDirection(Piece piece) {
        return piece.getColor() == Color.WHITE ? moveDirectionWhite : moveDirectionBlack;
    }

    /**
     * Gets a single diagonal capture move for a pawn.
     * 
     * @param piece The piece to get the capture move for
     * @param captureDirection The horizontal direction of capture (-1 for left, 1 for right)
     * @return A list containing the capture square if valid, or an empty list
     */
    private List<Square> getCaptureMove(Piece piece, int captureDirection) {
        var square = board.getSquare(piece);
        var moveDirection = getMoveDirection(piece);
        var captureSquare = board.getSquare(square.getRank() + moveDirection, square.getFile() + captureDirection);

        if (captureSquare != null && canCapture(square, captureSquare)) {
            return List.of(captureSquare);
        }

        return List.of(); 
    }

    /**
     * Checks if a pawn has moved from its starting position.
     * 
     * @param piece The piece to check
     * @return true if the pawn has moved, false if it is still at starting rank
     */
    private boolean hasMoved(Piece piece) {
        var square = board.getSquare(piece);
        var startingRank = piece.getColor() == Color.WHITE ? startingRankWhite : startingRankBlack;
        return square.getRank() != startingRank;
    }

    /**
     * Gets a forward move for a pawn.
     * 
     * @param piece The piece to get the forward move for
     * @param squareCount The number of squares to move forward (1 or 2)
     * @return A list containing the forward square if it is empty, or an empty list
     */
    private List<Square> getForwardMove(Piece piece, int squareCount) {
        var square = board.getSquare(piece);
        var direction = piece.getColor() == Color.WHITE ? squareCount : -squareCount;
        var nextSquare = board.getSquare(square.getRank() + direction, square.getFile());

        if (nextSquare != null && !nextSquare.hasPiece()) {
            return List.of(nextSquare);
        }

        return List.of();
    }
    
}
