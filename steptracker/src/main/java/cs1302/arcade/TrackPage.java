
import java.io.*;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;


import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.Priority;
import java.lang.IllegalArgumentException;

/**
 * Represents a basic image viewer app.
 */
public class ImageApp extends Application {

    private HBox hboxLogD;
    private HBox hboxLogA;
    private HBox hboxLogL;
    private HBox hboxLogo;
    private HBox hboxNav;
    private VBox vboxLog;

    private Button search;
    private Button submit;
    private Button home;
    private Button track;
    private Button step;

    private TextField dateBar;
    private TextField actBar;
    private TextField lengthBar;

    private BorderPane bp;

    private void initPage() {
        bp = new BorderPane();
        bp.setBackground(new Background(
            new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
        bp.setPrefSize(1024,576);

        initLogBox();
        initLogo();
        initNav();

        bp.setTop(hboxLogo);
        bp.setCenter(vboxLog);
        bp.setBottom(hboxNav);
        // bp.setAlignment(hboxLog, Pos.TOP_CENTER);
        // bp.setBottom(hboxLog);
    }

    private void initLogBox() {

        dateBar = new TextField();
        hboxLogD = new HBox();
        hboxLogD.setPrefWidth(720);
        // hboxLogD.setMaxWidth(720);
        hboxLogD.setBackground(new Background(
            new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        hboxLogD.getChildren().addAll(new Label("   Date:           "), dateBar);
        // hboxLogD.setAlignment(Pos.CENTER);

        actBar = new TextField();
        hboxLogA = new HBox(15);
        hboxLogA.setPrefWidth(720);
        // hboxLogA.setMaxWidth(720);
        hboxLogA.setBackground(new Background(
            new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        search = new Button("Search");
        hboxLogA.getChildren().addAll(new Label("   Activity:    "), actBar, search);
        // hboxLogA.setAlignment(Pos.CENTER);

        lengthBar = new TextField();
        submit = new Button("Submit");
        hboxLogL = new HBox(15);
        hboxLogL.setPrefWidth(720);
        // hboxLogL.setMaxWidth(720);
        hboxLogL.setBackground(new Background(
            new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        hboxLogL.getChildren()
            .addAll(new Label("   Time (m): "), lengthBar, submit);
        // hboxLogL.setAlignment(Pos.CENTER);

        vboxLog = new VBox();
        vboxLog.getChildren().addAll(hboxLogD, hboxLogA, hboxLogL);
    }

    private void handleDataBox() {
        EventHandler <ActionEvent> submitHandler = (ActionEvent event) -> {
            try {

                writeToFile(dateBar.getText());
                writeToFile(actBar.getText());
                writeToFile(lengthBar.getText());
                // System.out.println(dateBar.getText());
                // System.out.println(actBar.getText());
                // System.out.println(lengthBar.getText());
                // Image newImage = new Image(textField.getText());
                // imageView.setImage(newImage);

            } catch (IllegalArgumentException iae) {
                System.err.println("No Valid Data");
            }
        };

        submit.setOnAction(submitHandler);
    }

    private void writeToFile(String msg)  {
        String newLine = System.getProperty("line.separator");
        String fileName = "c:\\TEMP\\runOutput.txt";
        PrintWriter printWriter = null;
        File file = new File(fileName);
        try {
            if (!file.exists()) file.createNewFile();
            printWriter = new PrintWriter(new FileOutputStream(fileName, true));
            printWriter.write(newLine + msg);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    private void initLogo() {
        Image image = new Image("file:resources/log_text", 600, 200, true, true, false);
        ImageView logoIV = new ImageView(image);
        hboxLogo = new HBox();
        hboxLogo.getChildren().add(logoIV);
        hboxLogo.setAlignment(Pos.CENTER);

    }

    private void initNav() {
        hboxNav = new HBox();

        home = new Button();
        home.setBackground(createBack("file:resources/home"));
        home.setPrefSize(80, 90);

        track = new Button();
        track.setBackground(createBack("file:resources/foot"));
        track.setPrefSize(80, 90);

        step = new Button();
        step.setBackground(createBack("file:resources/track"));
        step.setPrefSize(80, 90);

        hboxNav.getChildren().addAll(home, track, step);
    }

    private Background createBack(String path) {
        BackgroundImage backgroundImage = new BackgroundImage(new Image(path, 80, 90, false, true),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return new Background(backgroundImage);

    }

    private void play() {
        initPage();
    }

/** Creates the start of the Application. */
    @Override
    public void start(Stage stage) {

        initPage();

        Scene scene = new Scene(bp);

        handleDataBox();


        stage.setScene(scene);
        stage.setTitle("cs1302 Image App!");
        stage.sizeToScene();
        stage.show();


    } // main

} // ImageApp
