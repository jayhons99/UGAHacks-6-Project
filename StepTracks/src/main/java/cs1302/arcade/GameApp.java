package cs1302.arcade;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

/**
 * This class is the Mancala application, that implements
 * Mancala.java into JavaFX.
 */
public class GameApp extends Application {

    Mancala game;

    Stage stage;
    VBox vbox;
    HBox boardHBox;

    TilePane tile = new TilePane();
    ImageView[] frames = new ImageView[16];

    ImageView topStoreIV;
    ImageView botStoreIV;

    Button exitBtn;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn10;
    Button btn11;
    Button btn12;

    HBox scoreHBox = new HBox();
    Text topScoreText = new Text();
    Text botScoreText = new Text();
    int topScore;
    int botScore;

    Scene scene1;
    Scene scene2;

    Button closeBtn = new Button("EXIT");
    Button restartBtn = new Button("RESTART");

    VBox layout1;

    /**
     * Start method to the Mancala application.
     */
    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        closeBtn.setOnAction (e -> {
            Platform.exit();
            System.exit(0);
        });
        // Below is all for the Main Menu Scene
        Label label1 = new Label("Minecala");
        Button b1 = new Button("PLAY");
        b1.setOnAction (e -> stage.setScene(scene2));
        layout1 = new VBox(20);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(label1, b1, closeBtn);
        infoText();
        scene1 = new Scene(layout1, 300, 300);
        //Back to Scene1 from Scene2
        Button b2 = new Button("Back 2 Main Menu");
        b2.setOnAction (e -> stage.setScene(scene1));
        ///////////////////////////////////////////////
        game = new Mancala();
        vbox = new VBox();
        boardHBox = new HBox();
        vbox.getChildren().addAll(b2, restartBtn);

        addTopButtons();
        addTopStore();
        addTiles();

        addBotStore();
        vbox.getChildren().add(boardHBox);
        addBotButtons();

        updateScore();
        scoreHBox.getChildren().addAll(topScoreText, botScoreText);
        scoreHBox.setSpacing(600);
        scoreHBox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(scoreHBox);
        eventHandlers();
        botEventHandlers();

        scene2 = new Scene(vbox, 800, 360);
        stage.setTitle("Efficient Minecala?");
        stage.setScene(scene1);
        stage.sizeToScene();
        stage.show();
    } // start


    /**
     * Adds some info text.
     */
    public void infoText() {
        Text t = new Text("Standard Mancala Rules\nCaptures Enabled\nRepeat-Turns Enabled" +
            "\nTop-Player-1 will always go First,\nTop-Player-1's board is on top");
        layout1.getChildren().add(t);
    } // infoText

    /**
     * This method adds the default 12 pits that each have 4 stones.
     */
    public void addTiles() {
        tile.setPrefColumns(6);
        tile.setPrefRows(2);
        // go through every element in the arrylist and check its value and set image accordingly
        for (int i = 0; i < 12; i++) {
            frames[i] = new ImageView();
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k <= 21; k++) {
                    if (game.getPitValueAt(j) == k) {
                        frames[i].setImage(new Image("file:src/main/resources/" + k + ".png"));
                    }
                }
            }
            frames[i].setFitHeight(100.0);
            frames[i].setFitWidth(100.0);
            tile.getChildren().add(frames[i]);
        }
        boardHBox.getChildren().add(tile);
    } // addTiles


    /**
     * Adds the default, empty Store on the left side, for the top player.
     */
    public void addTopStore() {
        topStoreIV = new ImageView();
        topStoreIV.setImage(new Image("file:src/main/resources/store/0.png"));
        topStoreIV.setFitWidth(100);
        topStoreIV.setFitHeight(200);
        boardHBox.getChildren().add(topStoreIV);
    } // addTopStore

    /**
     * Adds the default, empty Store on the right side, for the bottom player.
     */
    public void addBotStore() {
        botStoreIV = new ImageView();
        botStoreIV.setImage(new Image("file:src/main/resources/store/0.png"));
        botStoreIV.setFitWidth(100);
        botStoreIV.setFitHeight(200);
        boardHBox.getChildren().add(botStoreIV);
    } // addBotStore

    /**
     * Adds all the buttons, including exit, for the top player.
     */
    public void addTopButtons() {
        HBox topHBox = new HBox();
        topHBox.setSpacing(52);
        topHBox.setAlignment(Pos.CENTER);

        btn0 = new Button("Pit 0");
        btn1 = new Button("Pit 1");
        btn2 = new Button("Pit 2");
        btn3 = new Button("Pit 3");
        btn4 = new Button("Pit 4");
        btn5 = new Button("Pit 5");

        topHBox.getChildren().addAll(btn5, btn4, btn3, btn2, btn1, btn0);
        vbox.getChildren().add(topHBox);
    } //addTopButtons

    /**
     * Adds buttons for the bottom player.
     */
    public void addBotButtons() {
        HBox botHBox = new HBox();
        botHBox.setSpacing(55);
        botHBox.setAlignment(Pos.CENTER);
        btn6 = new Button("Pit 6");
        btn7 = new Button("Pit 7");
        btn8 = new Button("Pit 8");
        btn9 = new Button("Pit 9");
        btn10 = new Button("Pit 10");
        btn11 = new Button("Pit 11");

        botHBox.getChildren().addAll(btn6, btn7, btn8, btn9, btn10, btn11);
        vbox.getChildren().add(botHBox);
    } // addBotButtons

    /**
     * Updates and prints the score for both top and bottom player.
     */
    private void updateScore() {
        topScore = game.getTopStoreValue();
        botScore = game.getBotStoreValue();
        if (topScore > botScore) {
            topScoreText.setText("Top-Player-1\nScore: " + topScore + "\nWIN");
            botScoreText.setText("Bottom-Player-2\nScore: " + botScore + "\nLOSE");
        } else if (botScore > topScore) {
            topScoreText.setText("Top-Player-1\nScore: " + topScore + "\nLOSE");
            botScoreText.setText("Bottom-Player-2\nScore: " + botScore + "\nWIN");
        } else {
            topScoreText.setText("Top-Player-1\nScore: " + topScore + "\nDRAW");
            botScoreText.setText("Bottom-Player-2\nScore: " + botScore + "\nDRAW");
        }
    } // updateScore

    /**
     * This is the main event handler for all buttons, plays the game in the backend,
     * updates each pit and store, and updates the score.
     */
    private void eventHandlers() {

        // ABANDON ALL HOPE YE WHO ENTERS HERE!
        btn0.setOnAction(e -> {
            game.move(0);
            setAllFrames();
            updateScore();
        });

        btn1.setOnAction(e -> {
            game.move(1);
            setAllFrames();
            updateScore();
        });

        btn2.setOnAction(e -> {
            game.move(2);
            setAllFrames();
            updateScore();
        });

        btn3.setOnAction(e -> {
            game.move(3);
            setAllFrames();
            updateScore();
        });

        btn4.setOnAction(e -> {
            game.move(4);
            setAllFrames();
            updateScore();
        });

        btn5.setOnAction(e -> {
            game.move(5);
            setAllFrames();
            updateScore();
        });

    }

    /**
     * Implements all buttons for the bottom player.
     */
    private void botEventHandlers() {

        btn6.setOnAction(e -> {
            game.move(7);
            setAllFrames();
            updateScore();
        });

        btn7.setOnAction(e -> {
            game.move(8);
            setAllFrames();
            updateScore();
        });

        btn8.setOnAction(e -> {
            game.move(9);
            setAllFrames();
            updateScore();
        });

        btn9.setOnAction(e -> {
            game.move(10);
            setAllFrames();
            updateScore();
        });

        btn10.setOnAction(e -> {
            game.move(11);
            setAllFrames();
            updateScore();
        });

        btn11.setOnAction(e -> {
            game.move(12);
            setAllFrames();
            updateScore();
        });
    }

    /**
     * This method changes each pit and store according to {@code move}.
     */
    public void setAllFrames() {
        // PARAMETERS, for all cases of stones in pits.
        if (game.getPitValueAt(0) == 0 && game.getPitValueAt(1) == 0 &&
            game.getPitValueAt(2) == 0 && game.getPitValueAt(3) == 0 &&
            game.getPitValueAt(4) == 0 && game.getPitValueAt(5) == 0) {
            for (int i = 6; i < 12; i++) {
                frames[i].setImage(new Image("file:src/main/resources/0.png"));
            }
            for (int i = 0; i < 6; i++) {
                frames[i].setImage(new Image("file:src/main/resources/0.png"));
            }
            topStoreIV.setImage(new Image("file:src/main/resources/store/" +
                game.getTopStoreValue() + ".png"));

            botStoreIV.setImage(new Image("file:src/main/resources/store/" +
                game.getBotStoreValue() + ".png"));
        } else if (game.getPitValueAt(12) == 0 && game.getPitValueAt(7) == 0 &&
            game.getPitValueAt(8) == 0 && game.getPitValueAt(9) == 0 &&
            game.getPitValueAt(10) == 0 && game.getPitValueAt(11) == 0) {
            for (int i = 0; i < 6; i++) {
                frames[i].setImage(new Image("file:src/main/resources/0.png"));
            }
            for (int i = 6; i < 12; i++) {
                frames[i].setImage(new Image("file:src/main/resources/0.png"));
            }
            topStoreIV.setImage(new Image("file:src/main/resources/store/" +
                game.getTopStoreValue() + ".png"));
            botStoreIV.setImage(new Image("file:src/main/resources/store/" +
                game.getBotStoreValue() + ".png"));
        } else {
            frames[5].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(0) + ".png"));
            frames[4].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(1) + ".png"));
            frames[3].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(2) + ".png"));
            frames[2].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(3) + ".png"));
            frames[1].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(4) + ".png"));
            frames[0].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(5) + ".png"));
            topStoreIV.setImage(new Image("file:src/main/resources/store/" +
                game.getTopStoreValue() + ".png"));
            frames[6].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(7) + ".png"));
            frames[7].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(8) + ".png"));
            frames[8].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(9) + ".png"));
            frames[9].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(10) + ".png"));
            frames[10].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(11) + ".png"));
            frames[11].setImage(new Image("file:src/main/resources/" +
                game.getPitValueAt(12) + ".png"));
            botStoreIV.setImage(new Image("file:src/main/resources/store/" +
                game.getBotStoreValue() + ".png"));
        }
    } //setAllFram
} // ArcadeApp
