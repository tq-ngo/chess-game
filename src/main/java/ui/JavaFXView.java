package ui;

import game.Player;
import state.Board;

import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;

/**
 * Adapter implementing View. It blocks the background Game thread
 * until the user has successfully interacted with the JavaFX UI.
 */
public class JavaFXView implements View {
    private final ChessBoardUI boardUI;
    private volatile CompletableFuture<Void> turnFuture;

    public JavaFXView(ChessBoardUI boardUI) {
        this.boardUI = boardUI;
    }

    @Override
    public void show(Board board) {
        // Run strictly on the UI thread
        Platform.runLater(() -> boardUI.updateBoard(board));
    }

    @Override
    public void start(Player player, Board board) {
        turnFuture = new CompletableFuture<>();
        
        Platform.runLater(() -> {
            boardUI.updateBoard(board);
            boardUI.enableInteraction(player, board, turnFuture);
        });

        try {
            // Block the current thread (background game thread) until GUI completes it
            turnFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
