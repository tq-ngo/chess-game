package game;

import state.Board;
import util.Color;

/**
 * Abstract base class representing a player in a chess match.
 * Defines common properties and behaviors for all player types.
 */
public abstract class Player {
    private final Color color;
    protected Board board;
    
    /**
     * Constructs a Player with a specified color and board.
     * 
     * @param color The color of the player's pieces (WHITE or BLACK)
     * @param board The chess board this player plays on
     */
    public Player(Color color, Board board) {
        this.color = color;
        this.board = board;
    }

    /**
     * Gets the color of this player's pieces.
     * 
     * @return The color (WHITE or BLACK) assigned to this player
     */
    public Color getColor() { return color; }
}
