package core;

import java.io.Console;

import game.HumanPlayer;
import game.Match;
import state.BoardFactory;
import ui.Console;
import util.Color;

/**
 * Main entry point for the Chess game.
 * Initializes a chess game between two human players on a conventional chess board.
 */
public class Main {
    /**
     * Main method that starts a chess game.
     * Creates a standard 8x8 chess board, initializes two human players,
     * and starts a match between them.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        var board = BoardFactory.create();
        var player1 = new HumanPlayer(Color.WHITE, board);
        var player2 = new HumanPlayer(Color.BLACK, board);
        var console = new Console();
        var match = new Match(player1, player2, board, new rules.Rulebook(), console);
        match.start();
    }
}
