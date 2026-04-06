package state;

import pieces.Piece;

/**
 * Represents a single square on the chess board.
 * A square has a position (rank and file) and may contain a chess piece.
 */
public class Square {
    private final int rank, file;
    private Piece piece;

    /**
     * Constructs a Square at the given file and rank coordinates.
     * 
     * @param file The file (column) coordinate (0-7)
     * @param rank The rank (row) coordinate (0-7)
     */
    public Square(int file, int rank) {
        this.file = file;
        this.rank = rank;
        this.piece = null;
    }

    /**
     * Gets the file (column) coordinate of this square.
     * 
     * @return The file coordinate (0-7)
     */
    public int getFile() {
        return file;
    }

    /**
     * Gets the rank (row) coordinate of this square.
     * 
     * @return The rank coordinate (0-7)
     */
    public int getRank() {
        return rank;
    }

    /**
     * Gets the piece currently on this square.
     * 
     * @return The piece on this square, or null if the square is empty
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Checks if this square currently contains a piece.
     * 
     * @return true if the square contains a piece, false otherwise
     */
    public boolean hasPiece() {
        return piece != null;
    }

    /**
     * Places a piece on this square.
     * Updates the piece's position to match this square's coordinates.
     * 
     * @param piece The piece to place on this square, or null to clear the square
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            this.piece.place(file, rank);
        }
    }
    
    /**
     * Returns the algebraic notation representation of this square.
     * Format: file letter (a-h) + rank number (1-8)
     * 
     * @return The algebraic notation of this square (e.g., "e4")
     */
    @Override
    public String toString() {
        char fileChar = (char) ('a' + file);
        int rankNum = rank + 1;
        return "" + fileChar + rankNum;
    }
}
