package game;

import rules.Rulebook;
import state.Board;
import ui.View;

/**
 * Represents a chess match between two players.
 * Manages the game loop, turn-taking, and game completion.
 */
public class Match {
    private final Player player1, player2;
    private final View view;
    private final Board board;
    private final Rulebook rules;
    
    /**
     * Constructs a Match with two players and game components.
     * 
     * @param player1 The first player to move (typically white)
     * @param player2 The second player to move (typically black)
     * @param board The chess board for this match
     * @param rules The rulebook governing legal moves
     * @param view The view component for displaying the board to players
     */
    public Match(Player player1 , Player player2, Board board, Rulebook rules, View view) {
        this.player1 = player1;
        this.player2 = player2;
        this.view = view;
        this.board = board;
        this.rules = rules;
    }

    /**
     * Starts the chess match.
     * Alternates between players until one achieves checkmate.
     * Displays board state and available moves to each player.
     */
    public void start() {
        while (true) { 
            view.start(player1, board);
            if (rules.isGameOver(board, player2.getColor())) {
                System.out.println("Player " + player1.getColor() + " wins!");
                break;
            }
            view.start(player2, board);
            if (rules.isGameOver(board, player1.getColor())) {
                System.out.println("Player " + player2.getColor() + " wins!");
                break;
            }
        }
    }
}
