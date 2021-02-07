package cs1302.gallery;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.HBox;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.PasswordField;


/** 
 * Represents an iTunes GalleryApp!
 */
public class GalleryApp extends Application {
    GridPane grid;
    Label username;
    Label password;
    TextField userField;
    PasswordField passField;
    Scene signedIn;
    Button signIn;
    Button signUp;
    
    /** {@inheritdoc} */
    @Override
    public void start(Stage stage) {

	// Color Background
	StackPane pane = new StackPane();
	Rectangle colorBackground = new Rectangle(1024, 576);
	Rectangle whiteBackground = new Rectangle(350, 350);
	colorBackground.setFill(Color.LIGHTCYAN);
	whiteBackground.setFill(Color.WHITE);

	// Grid for Items
	grid = new GridPane();
	grid.setAlignment(Pos.CENTER);
	grid.setHgap(8);
	grid.setVgap(8);
	grid.setPadding(new Insets(20,20,20,20));

	// Add items to Grid
	textFields();

	// Add Grid / Background to Scene
	pane.getChildren().addAll(colorBackground, whiteBackground, grid);

	// Button Functionality
	signIn.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
		    signedInMeth(stage);
		}
	    });

	signUp.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
		    signUpPage(stage);
		}
	    });
	
        Scene scene = new Scene(pane, 1024, 576);

	// These are the texts for the username and pass for sign in.
	String usernameText = userField.getText();
	String passwordText = passField.getText();
        
        stage.setTitle("Login Page");
        stage.setScene(scene);
	stage.sizeToScene();
        stage.show();
    } // start

    public void signUpPage(Stage stage) {
	StackPane pane = new StackPane();
	Rectangle colorBackground = new Rectangle(1024, 572);
	Rectangle whiteBackground = new Rectangle(350, 350);
	colorBackground.setFill(Color.LIGHTPINK);
	whiteBackground.setFill(Color.WHITE);

	// Grid for Items
	GridPane signUpGrid = new GridPane();
	signUpGrid.setAlignment(Pos.CENTER);
	signUpGrid.setHgap(8);
	signUpGrid.setVgap(8);
	signUpGrid.setPadding(new Insets(20,20,20,20));

	// SignUp screen
	Text signUpText = new Text("Sign Up");
	signUpText.setFont(Font.font("Quicksand", FontWeight.BOLD, 25));
	signUpGrid.add(signUpText, 0, 0, 2, 1);

	// Username and Password
	Label newUsername = new Label("Username: ");
	Label newPassword = new Label("Password: ");
	Label repeatPassword = new Label("Repeat Password: ");
	TextField newUserField = new TextField();
	PasswordField newPassField = new PasswordField();
	PasswordField repeatPassField = new PasswordField();
	
	// Add to Grid
	signUpGrid.add(newUsername, 0, 1);
	signUpGrid.add(newUserField, 1, 1);
	signUpGrid.add(newPassword, 0, 2);
	signUpGrid.add(newPassField, 1, 2);
	signUpGrid.add(repeatPassword, 0, 3);
	signUpGrid.add(repeatPassField, 1, 3);
	
	// Buttons
	Button signUpConfirm = new Button("Sign Up");
	
	HBox signUpBox = new HBox(10);
	signUpBox.setAlignment(Pos.BOTTOM_RIGHT);

	signUpBox.getChildren().add(signUpConfirm); 

	// signUpConfirm button function and check if pass are same
	Text text = new Text("");
	text.setFont(Font.font("verdana", FontWeight.BOLD, 15));
	
	signUpConfirm.setOnAction(e -> {
		String pass = newPassField.getText();
		String repeatPass = repeatPassField.getText();

		if (pass.equals(repeatPass)) {
		    text.setText("You have signed up!");
		} else {
		    text.setText("Your passwords do NOT match. Please try again.");
		}
	    });
	
	// Add Buttons to Grid
	signUpGrid.add(signUpBox, 0, 4);
	HBox box = new HBox();
	box.getChildren().add(text);

	// Change Scene
	pane.getChildren().addAll(colorBackground, whiteBackground, signUpGrid, box);
	
	Scene signedUp = new Scene(pane, 1280, 5720);
	stage.setScene(signedUp);

    }

    /** 
     * This method is just a filler scene for where the program is supposed to go after sign in. 
     */
    public void signedInMeth(Stage stage) {
	StackPane pane = new StackPane();
	Rectangle colorBackground = new Rectangle(1024, 576);
	Rectangle whiteBackground = new Rectangle(300, 300);
	colorBackground.setFill(Color.LIGHTGREEN);
	whiteBackground.setFill(Color.WHITE);

	HBox signedInMessage = new HBox();
	//signedInMessage.setPos(Pos.CENTER); // maybe messed up???
	Label youHaveSignedIn = new Label("You have successfully signed in");
	youHaveSignedIn.setFont(Font.font("Quicksand", FontWeight.NORMAL, 20));
	signedInMessage.getChildren().add(youHaveSignedIn);

	pane.getChildren().addAll(colorBackground, whiteBackground, signedInMessage);
	
	
	signedIn = new Scene(pane, 1280, 5720);
	stage.setScene(signedIn);
    }

    public void textFields() {
	// login screen
	Text login = new Text("Login");
	login.setFont(Font.font("Quicksand", FontWeight.BOLD, 25));
	grid.add(login, 0, 0, 2, 1);

	// Username and Password
	username = new Label("Username: ");
	password = new Label("Password: ");
	userField = new TextField();
	passField = new PasswordField();

	// Add to Grid
	grid.add(username, 0, 1);
	grid.add(userField, 1, 1);
	grid.add(password, 0, 2);
	grid.add(passField, 1, 2);

	// Buttons
	signIn = new Button("Sign In");
	signUp = new Button("Sign Up");

	HBox inBox = new HBox(10);
	inBox.setAlignment(Pos.BOTTOM_RIGHT);
	HBox upBox = new HBox(10);
	upBox.setAlignment(Pos.BOTTOM_LEFT);

	inBox.getChildren().add(signIn);
	upBox.getChildren().add(signUp);
	
	// Add Buttons to Grid
	grid.add(inBox, 1, 3);
	grid.add(upBox, 0, 3);
    }

} // GalleryApp

