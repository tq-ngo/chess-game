package state;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Queen;
import pieces.Rook;
import util.Color;

/**
 * Factory for creating a standard chess board with conventional piece setup.
 * Creates an 8x8 board with all pieces in their starting positions.
 */
public class BoardFactory {
    /**
     * Creates and returns a standard chess board with conventional setup.
     * Sets up all 32 pieces (16 white and 16 black) in their starting positions.
     * 
     * @return A fully initialized standard chess board
     */
    public static Board create(){
        var board = new Board(8,8);
        addPieces(Color.WHITE, 0, board);
        addPieces(Color.BLACK, 7, board);
        addPawns(Color.WHITE, 1, board);
        addPawns(Color.BLACK, 6, board);
        return board;
    }

    /**
     * Adds all pawns for a specified color to the board.
     * 
     * @param color The color of the pawns to add
     * @param rank The rank on which to place the pawns
     * @param board The board to add pawns to
     */
    private static void addPawns(Color color, int rank, Board board) {
        for (int file = 0; file < 8; file++) {
            board.getSquare(rank,file).setPiece(new pieces.Pawn(color));
        }
    }

    /**
     * Adds all non-pawn pieces for a specified color to the board.
     * Places rooks, knights, bishops, queen, and king in their starting positions.
     * 
     * @param color The color of the pieces to add
     * @param rank The rank on which to place the pieces
     * @param board The board to add pieces to
     */
    private static void addPieces(Color color, int rank, Board board) {
        board.getSquare(rank,0).setPiece(new Rook(color));
        board.getSquare(rank,7).setPiece(new Rook(color));
        board.getSquare(rank,1).setPiece(new Knight(color));
        board.getSquare(rank,6).setPiece(new Knight(color));
        board.getSquare(rank,2).setPiece(new Bishop(color));
        board.getSquare(rank,5).setPiece(new Bishop(color));
        board.getSquare(rank,3).setPiece(new Queen(color));
        board.getSquare(rank,4).setPiece(new King(color));
    }
}
