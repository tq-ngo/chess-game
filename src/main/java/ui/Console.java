package ui;

import java.util.ArrayList;
import java.util.Scanner;

import game.Player;
import pieces.Piece;
import rules.Move;
import state.Board;

/**
 * Console-based view implementation for displaying chess board and managing user input.
 * Provides text-based interface for players to view the board and select moves.
 */
public class Console implements View {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a message to the console.
     * 
     * @param message The message to print
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Prints the chess board to the console.
     * Displays the board from rank 7 to 0 (top to bottom).
     * 
     * @param board The board to print
     */
    private void print(Board board) {
        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                Piece piece = board.getSquare(rank, file).getPiece();
                if (piece == null) {
                    System.out.print(". ");
                } else {
                    var symbol = piece.toString();
                    System.out.print(symbol + " ");
                }
            }
            print("");
        }
    }

    /**
     * Displays the board to the user.
     * 
     * @param board The board to display
     */
    @Override
    public void show(Board board) {
        print(board);
    }

    /**
     * Reads a move selection from the user.
     * 
     * @param board The board (unused, kept for interface compatibility)
     * @return The user's selected move number
     */
    public int getMove(Board board) {
        return scanner.nextInt();
    }

    /**
     * Displays the board and handles move selection for a player's turn.
     * Displays available legal moves and prompts the player to select one.
     * 
     * @param player The current player
     * @param board The current board state
     */
    @Override
    public void start(Player player, Board board) {
        while (true) {
            show(board);
            var moves = getMoves(player, board);
            print(moves);
            print("Enter the number of your next move:");
            var move = getMove(board);
            if (move > 0 && move <= moves.size()) {
                moves.get(move - 1).execute();
                print("Move executed: " + moves.get(move - 1).toString());
                return;
            } else {
                print("Illegal move: " + move);
            }
        }
    }

    /**
     * Prints a numbered list of moves to the console.
     * 
     * @param moves The list of moves to display
     */
    private void print(ArrayList<Move> moves) {
        for (int i = 0; i < moves.size(); i++) {
            print((i + 1) + ": " + moves.get(i));
        }
    }

    /**
     * Gets all legal moves available for a player.
     * Retrieves possible moves for all pieces of the player's color.
     * 
     * @param player The player to get moves for
     * @param board The current board state
     * @return A list of all legal moves available to the player
     */
    private ArrayList<Move> getMoves(Player player, Board board) {
        var moves = new ArrayList<Move>();
        var pieces = board.getPieces(player.getColor());
        for (var piece : pieces) {
            var legalMoves = piece.possibleMoves(board);
            for (var toSquare : legalMoves) {
                var fromSquare = board.getSquare(piece);
                moves.add(new Move(fromSquare, toSquare));
            }
        }
        return moves;
    }

    
}
