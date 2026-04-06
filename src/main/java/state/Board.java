package state;

import java.util.ArrayList;

import pieces.Piece;
import util.Color;

/**
 * Represents the chess board containing all squares and pieces.
 * Manages the 2D grid of squares and provides queries for piece locations.
 */
public class Board {
    private final Square[][] squares;

    /**
     * Constructs a Board with the specified dimensions.
     * Initializes all squares in the grid with their coordinates.
     * 
     * @param ranks The number of ranks (rows) on the board
     * @param files The number of files (columns) on the board
     */
    public Board(int ranks, int files) {
        squares = new Square[ranks][files];
        for (int rank = 0; rank < ranks; rank++) {
            for (int file = 0; file < files; file++) {
                squares[rank][file] = new Square(file, rank);
            }
        }
    }

    /**
     * Finds the square containing the specified piece.
     * 
     * @param piece The piece to locate
     * @return The square containing the piece, or null if not found
     */
    public Square getSquare(Piece piece) {
        for (var row : squares) {
            for (var square : row){
                if (square.getPiece() == piece) {
                    return square;
                }
            }
        }
        return null;
    }

    /**
     * Gets the square at the specified rank and file coordinates.
     * 
     * @param rank The rank (row) coordinate
     * @param file The file (column) coordinate
     * @return The square at the given coordinates, or null if out of bounds
     */
    public Square getSquare(int rank, int file) {
        if (rank >= 0 && rank < squares.length && 
            file >= 0 && file < squares[0].length) {
            return squares[rank][file];
        }
        return null;
    }

    /**
     * Gets all pieces of a specified color on the board.
     * 
     * @param color The color of pieces to retrieve (WHITE or BLACK)
     * @return A list of all pieces of the specified color
     */
    public ArrayList<Piece> getPieces(Color color) {
        var pieces = new ArrayList<Piece>();
        for (var row : squares) {
            for (var square : row){
                if (square.getPiece() != null && square.getPiece().getColor() == color) {
                    pieces.add(square.getPiece());
                }
            }
        }
        return pieces;
    }

    /**
     * Gets all pieces of a specified color and type on the board.
     * 
     * @param color The color of pieces to retrieve (WHITE or BLACK)
     * @param pieceType The class type of pieces to retrieve (e.g., Pawn.class, Rook.class)
     * @return A list of all pieces matching the specified color and type
     */
    public ArrayList<Piece> getPieces(Color color, Class<? extends Piece> pieceType) {
        var pieces = new ArrayList<Piece>();
        for (var row : squares) {
            for (var square : row){
                var piece = square.getPiece();
                if (piece != null && 
                pieceType.isInstance(piece) && 
                piece.getColor() == color) {
                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }

    /**
     * Creates a deep copy of this board with all pieces.
     * The new board has the same layout and piece configuration.
     * 
     * @return A new Board object that is an independent copy of this board
     */
    public Board copy() {
        var newBoard = new Board(getSize(), getSize());
        for (var row : squares) {
            for (var square : row){
                if (square.getPiece() != null) {
                    var newPiece = square.getPiece().copy();
                    newBoard.getSquare(square.getRank(), square.getFile()).setPiece(newPiece);
                }
            }
        }
        return newBoard;
    }

    /**
     * Gets the size (number of ranks/files) of this board.
     * Assumes a square board.
     * 
     * @return The size of the board (typically 8 for standard chess)
     */
    public int getSize() {
        return squares.length;
    }
}
