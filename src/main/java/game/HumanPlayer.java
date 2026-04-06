package game;

import state.Board;
import util.Color;

/**
 * Represents a human player in a chess game.
 * A human player interacts with the game through the console interface.
 */
public class HumanPlayer extends  Player {
    /**
     * Constructs a HumanPlayer with a specified color and board.
     * 
     * @param color The color of the player's pieces (WHITE or BLACK)
     * @param board The chess board this player plays on
     */
    public HumanPlayer(Color color, Board board) {
        super(color, board);
    }
}
