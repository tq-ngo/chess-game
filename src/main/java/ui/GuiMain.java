package ui;

import game.HumanPlayer;
import game.Match;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import state.BoardFactory;
import util.Color;

/**
 * JavaFX Entry Point. Initializes the game structure and runs the blocking
 * Match game-loop inside a background thread to prevent UI freezing.
 */
public class GuiMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        ChessBoardUI boardUI = new ChessBoardUI();
        JavaFXView guiView = new JavaFXView(boardUI);

        BorderPane root = new BorderPane();
        root.setCenter(boardUI.getView());

        Scene scene = new Scene(root, 640, 640);
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        
        // Ensure JVM stops when window is closed
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        
        primaryStage.show();

        // Start game logic on a separate thread
        Thread gameThread = new Thread(() -> {
            var board = BoardFactory.create();
            var player1 = new HumanPlayer(Color.WHITE, board);
            var player2 = new HumanPlayer(Color.BLACK, board);
            var rulebook = new rules.Rulebook();
            
            var match = new Match(player1, player2, board, rulebook, guiView);
            match.start();
            
            System.out.println("Game Over!"); // console print when game is over
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// mvn clean compile javafx:run