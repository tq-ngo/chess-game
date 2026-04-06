package rules;

import java.util.ArrayList;

import pieces.Piece;
import state.Board;
import state.Square;
import util.Color;

/**
 * Enforces the rules of chess.
 * Determines legal moves, validates move legality, and checks for game end conditions.
 */
public class Rulebook {
    /**
     * Checks if a move is legal according to chess rules.
     * A move is legal if executing it does not leave the player's king in check.
     * 
     * @param move The move to validate
     * @param board The current board state
     * @return true if the move is legal, false otherwise
     */
    private boolean isLegal(Move move, Board board) {
        var boardCopy = board.copy();
        var moveCopy = move.copy(boardCopy);
        moveCopy.execute();
        return !kingInCheck(move.getColor(), boardCopy);
    }

    /**
     * Checks if the king of a specified color is currently in check.
     * A king is in check if any opponent piece can capture it.
     * 
     * @param color The color of the king to check
     * @param board The current board state
     * @return true if the king is in check, false otherwise
     */
    private boolean kingInCheck(Color color, Board board) {
        var opponentColor = color == Color.WHITE ? Color.BLACK : Color.WHITE;
        var pieces = board.getPieces(opponentColor);
        for (var piece : pieces) {
            var possibleMoves = piece.possibleMoves(board);
            for (var targetSquare : possibleMoves) {
                if (targetSquare.getPiece() != null &&
                    targetSquare.getPiece().getColor() == color &&
                    targetSquare.getPiece() instanceof pieces.King) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if the game is over for a specified player.
     * The game is over (checkmate) when the player has no legal moves available.
     * 
     * @param board The current board state
     * @param player The color of the player to check
     * @return true if the player is in checkmate (no legal moves), false otherwise
     */
    public boolean isGameOver(Board board, Color player) {
        var pieces = board.getPieces(player);
        for (var piece : pieces) {
            var legalMoves = getLegalMoves(piece, board);
            if (!legalMoves.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets all legal moves available for a specified piece.
     * Only includes moves that do not leave the player's king in check.
     * 
     * @param piece The piece to get legal moves for
     * @param board The current board state
     * @return A list of legal target squares the piece can move to
     */
    public ArrayList<Square> getLegalMoves(Piece piece, Board board) {
        var currentSquare = board.getSquare(piece.getRank(), piece.getFile());
        var possibleMoves = piece.possibleMoves(board);
        var legalMoves = new ArrayList<Square>();
        for (var targetSquare : possibleMoves) {
            var move = new Move(currentSquare, targetSquare);
            if (isLegal(move, board)) {
                legalMoves.add(targetSquare);
            }
        }
        return legalMoves;
    }
    
}
