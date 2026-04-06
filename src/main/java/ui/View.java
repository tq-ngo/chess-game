package ui;

import game.Player;
import state.Board;

/**
 * Interface for displaying the chess board and managing user interaction.
 * Defines how the board is displayed and how players interact with the game.
 */
public interface View {
    /**
     * Displays the chess board to the user.
     * 
     * @param board The board to display
     */
    void show(Board board);
    
    /**
     * Displays the board and manages a player's turn.
     * Handles board display, move selection, and move execution.
     * 
     * @param player The player whose turn it is
     * @param board The current board state
     */
    void start(Player player, Board board);
}
