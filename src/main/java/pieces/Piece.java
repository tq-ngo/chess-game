package pieces;

import java.util.ArrayList;

import state.Board;
import state.Square;
import util.Color;

/**
 * Abstract base class for all chess pieces.
 * Defines common properties and behaviors for all piece types.
 */
public abstract class Piece {
    protected final Color color;
    protected int file, rank;
    
    /**
     * Constructs a Piece with a specified color.
     * 
     * @param color The color of this piece (WHITE or BLACK)
     */
    public Piece(Color color) {
        this.color = color;
    }

    /**
     * Gets the color of this piece.
     * 
     * @return The color (WHITE or BLACK) of this piece
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Gets the file (column) coordinate of this piece's current position.
     * 
     * @return The file coordinate (0-7)
     */
    public int getFile() {
        return file;
    }
    
    /**
     * Gets the rank (row) coordinate of this piece's current position.
     * 
     * @return The rank coordinate (0-7)
     */
    public int getRank() {
        return rank;
    }

    /**
     * Places this piece at the specified coordinates.
     * Updates the piece's position on the board.
     * 
     * @param file The file (column) coordinate to place the piece at
     * @param rank The rank (row) coordinate to place the piece at
     */
    public void place(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }
    
    /**
     * Gets the point value of this piece.
     * Used for evaluating board position and strategy.
     * 
     * @return The point value of this piece type
     */
    public abstract int getValue();

    /**
     * Gets all squares this piece can move to (ignoring check constraints).
     * Returns the piece's possible moves based on its movement rules.
     * 
     * @param board The board state to determine possible moves
     * @return A list of squares the piece can move to
     */
    public abstract ArrayList<Square> possibleMoves(Board board);

    /**
     * Creates an independent copy of this piece.
     * Used for board state analysis without modifying the original piece.
     * 
     * @return A new Piece object that is a copy of this piece
     */
    public abstract Piece copy();
}
