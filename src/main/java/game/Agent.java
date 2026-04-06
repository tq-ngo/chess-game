package game;

import state.Board;
import util.Color;

/**
 * Represents an AI agent player in a chess game.
 * An agent player can be programmed to make automated moves.
 */
public class Agent extends Player {
    /**
     * Constructs an Agent with a specified color and board.
     * 
     * @param color The color of the agent's pieces (WHITE or BLACK)
     * @param board The chess board the agent plays on
     */
    public Agent(Color color, Board board) {
        super(color, board);
    }
}
