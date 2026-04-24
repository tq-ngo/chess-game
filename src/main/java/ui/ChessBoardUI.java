package ui;

import game.Player;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import pieces.Piece;
import rules.Move;
import rules.Rulebook;
import state.Board;
import state.Square;
import util.Color;


import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Manages the visual 8x8 Grid, user clicks, highlighting, and triggers moves.
 */
public class ChessBoardUI {
    private final GridPane grid;
    private final StackPane[][] panes = new StackPane[8][8];
    private final ImageView[][] pieceViews = new ImageView[8][8];
    private final Circle[][] moveDots = new Circle[8][8];
    
    private final Rulebook rulebook = new Rulebook();
    
    private Board currentBoard;
    private Player currentPlayer;
    private CompletableFuture<Void> turnFuture;
    
    private Square selectedSquare = null;
    private final ArrayList<Move> validMoves = new ArrayList<>();
    
    private Consumer<String> statusListener;

    private static final javafx.scene.paint.Color LIGHT_SQ = javafx.scene.paint.Color.rgb(240, 217, 181);
    private static final javafx.scene.paint.Color DARK_SQ = javafx.scene.paint.Color.rgb(181, 136, 99);
    private static final javafx.scene.paint.Color HIGHLIGHT_SELECTED = javafx.scene.paint.Color.rgb(255, 255, 102);
    private static final javafx.scene.paint.Color HIGHLIGHT_CHECK = javafx.scene.paint.Color.rgb(220, 50, 50);

    public ChessBoardUI() {
        grid = new GridPane();
        
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                StackPane pane = new StackPane();
                pane.setPrefSize(80, 80);
                
                ImageView imageView = new ImageView();
                // Scale image to fit inside the square
                imageView.setFitWidth(65);
                imageView.setFitHeight(65);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);

                // Create the dot for valid moves
                Circle dot = new Circle(14);
                dot.setFill(javafx.scene.paint.Color.rgb(0, 0, 0, 0.25)); // Semi-transparent black
                dot.setVisible(false); // Hidden by default
                dot.setMouseTransparent(true); // Allow clicks to pass through to the pane
                
                pane.getChildren().addAll(imageView, dot);

                int finalRank = rank;
                int finalFile = file;
                pane.setOnMouseClicked(e -> handleSquareClick(finalRank, finalFile));

                panes[rank][file] = pane;
                pieceViews[rank][file] = imageView;
                moveDots[rank][file] = dot;
                
                // Add to GridPane (rank 7 is top -> row 0)
                grid.add(pane, file, 7 - rank);
            }
        }
        resetColors();
    }

    public GridPane getView() {
        return grid;
    }

    public void setStatusListener(Consumer<String> listener) {
        this.statusListener = listener;
    }

    public void setStatus(String status) {
        if (statusListener != null) statusListener.accept(status);
    }

    public void updateBoard(Board board) {
        this.currentBoard = board;
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece p = board.getSquare(rank, file).getPiece();
                pieceViews[rank][file].setImage(getPieceImage(p));
            }
        }
        resetColors();
    }

    public void enableInteraction(Player player, Board board, CompletableFuture<Void> turnFuture) {
        this.currentPlayer = player;
        this.currentBoard = board;
        this.turnFuture = turnFuture;
        setStatus("Turn: " + player.getColor());
        clearSelection();
    }

    private void handleSquareClick(int rank, int file) {
        if (currentPlayer == null || turnFuture == null) return; // Not our turn

        Square clickedSquare = currentBoard.getSquare(rank, file);
        Piece clickedPiece = clickedSquare.getPiece();

        if (selectedSquare != null) {
            // if clicking the exact same square, deselect and return.
            if (selectedSquare == clickedSquare) {
                clearSelection();
                return;
            }

            // if clicked square is a valid target
            Move validMove = null;
            for (Move m : validMoves) {
                if (m.getTarget().getRank() == rank && m.getTarget().getFile() == file) {
                    validMove = m;
                    break;
                }
            }

            if (validMove != null) {
                // Execute move
                validMove.execute();
                
                // Clean up turn state
                clearSelection();
                CompletableFuture<Void> futureToComplete = turnFuture;
                turnFuture = null;
                currentPlayer = null;
                updateBoard(currentBoard); // Visual update
                
                futureToComplete.complete(null); // Unblock match thread
                return;
            } else if (clickedPiece != null && clickedPiece.getColor() == currentPlayer.getColor()) {
                // Clicked another of our own pieces, select it instead
                selectSquare(clickedSquare);
                return;
            } else {
                // Invalid square clicked, clear selection
                clearSelection();
                return;
            }
        }

        // Nothing was selected. Select the piece if it belongs to current player
        if (clickedPiece != null && clickedPiece.getColor() == currentPlayer.getColor()) {
            selectSquare(clickedSquare);
        }
    }

    private void selectSquare(Square square) {
        clearSelection();
        selectedSquare = square;
        
        // Highlight selection
        colorSquare(square.getRank(), square.getFile(), HIGHLIGHT_SELECTED);

        // Get legal moves (Using rulebook to ensure checking logic is intact)
        validMoves.clear();
        ArrayList<Square> legalTargets = rulebook.getLegalMoves(square.getPiece(), currentBoard);
        for (Square target : legalTargets) {
            validMoves.add(new Move(square, target));
            // Show the dot for valid targets
            moveDots[target.getRank()][target.getFile()].setVisible(true);
        }
    }

    private void clearSelection() {
        selectedSquare = null;
        validMoves.clear();
        resetColors();

        // Hide all move dots
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                moveDots[rank][file].setVisible(false);
            }
        }
    }

    private void resetColors() {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                boolean isLight = (rank + file) % 2 != 0;
                colorSquare(rank, file, isLight ? LIGHT_SQ : DARK_SQ);
            }
        }

         // Apply Red Highlight if a King is currently in check
        if (currentBoard != null) {
            highlightKingIfInCheck(Color.WHITE);
            highlightKingIfInCheck(Color.BLACK);
        }
    }

    /**
     * Finds the king of the given color and colors its square red if it is in check.
     */
    private void highlightKingIfInCheck(Color color) {
        if (isKingInCheck(currentBoard, color)) {
            for (Piece p : currentBoard.getPieces(color)) {
                if (p instanceof pieces.King) {
                    Square sq = currentBoard.getSquare(p);
                    colorSquare(sq.getRank(), sq.getFile(), HIGHLIGHT_CHECK);
                    break;
                }
            }
        }
    }

    // Looks at all opponent pieces to see if they can land on the King's square.
    private boolean isKingInCheck(Board board, Color color) {
        Color opponentColor = (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
        for (Piece piece : board.getPieces(opponentColor)) {
            for (Square target : piece.possibleMoves(board)) {
                Piece targetPiece = target.getPiece();
                if (targetPiece != null && targetPiece.getColor() == color && targetPiece instanceof pieces.King) {
                    return true;
                }
            }
        }
        return false;
    }

    private void colorSquare(int rank, int file, javafx.scene.paint.Color color) {
        panes[rank][file].setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private Image getPieceImage(Piece piece) {
        if (piece == null) return null;
        
        String prefix = (piece.getColor() == Color.WHITE) ? "w_" : "b_";
        String typeName = "";
        
        if (piece instanceof pieces.King) typeName = "king";
        else if (piece instanceof pieces.Queen) typeName = "queen";
        else if (piece instanceof pieces.Rook) typeName = "rook";
        else if (piece instanceof pieces.Bishop) typeName = "bishop";
        else if (piece instanceof pieces.Knight) typeName = "knight";
        else if (piece instanceof pieces.Pawn) typeName = "pawn";

        String imagePath = "/pieces/" + prefix + typeName + ".png";
        
        try {
            // Load the image from the resources folder
            return new Image(getClass().getResourceAsStream(imagePath));
        } catch (Exception e) {
            System.err.println("Could not find image: " + imagePath);
            return null; // render a blank space if image is missing
        }
    }
}

