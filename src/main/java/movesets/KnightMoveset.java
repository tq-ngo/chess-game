package movesets;

import java.util.ArrayList;

import pieces.Piece;
import state.Board;
import state.Square;

/**
 * Defines knight movement patterns for chess pieces.
 * Handles the unique L-shaped movement of the knight piece.
 */
public class KnightMoveset extends Moveset {
    /**
     * Constructs a KnightMoveset.
     * 
     * @param board The board on which moves will be calculated
     */
    public KnightMoveset(Board board) {
        super(board);
    }

    /**
     * Gets all possible knight moves for a piece.
     * Knights move in an L-shape: 2 squares in one direction and 1 square perpendicular.
     * 
     * @param piece The piece to get knight moves for
     * @return A list of squares the piece can move to
     */
    @Override
    public ArrayList<Square> getPossibleMoves(Piece piece) {
        var moves = new ArrayList<Square>();
        var square = board.getSquare(piece);

        var move = getPossibleMove(square, 2, 1);
        if (move != null) moves.add(move);
        
        move = getPossibleMove(square, 2, -1);
        if (move != null) moves.add(move);

        move = getPossibleMove(square, -2, 1);
        if (move != null) moves.add(move);

        move = getPossibleMove(square, -2, -1);
        if (move != null) moves.add(move);

        move = getPossibleMove(square,  1,  2);
        if (move != null) moves.add(move);
        
        move = getPossibleMove(square, 1, - 2);
        if (move != null) moves.add(move);
        
        move = getPossibleMove(square, - 1,2);
        if (move != null) moves.add(move);
        
        move = getPossibleMove(square, - 1, - 2);
        if (move != null) moves.add(move);
        
        return moves;
    }

    /**
     * Gets a single possible knight move in the specified direction.
     * 
     * @param square The square the knight is on
     * @param addRank The rank offset to move
     * @param addFile The file offset to move
     * @return The target square if valid and not blocked by own piece, null otherwise
     */
    private Square getPossibleMove(Square square, int addRank, int addFile) {
        var nextSquare = board.getSquare(square.getRank() + addRank, square.getFile() + addFile);

        if (nextSquare != null &&
            (!nextSquare.hasPiece() || canCapture(square, nextSquare))) {
            return nextSquare;
        }

        return null;
    }
    
}
