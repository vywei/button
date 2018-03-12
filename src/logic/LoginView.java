package logic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginView 
{
    private final BorderPane view;
     
    public LoginView()
    {
      
        view = new BorderPane();
        VBox loginBox = new VBox();
        double loginWidth = 200;
        double height = Main.SCREEN_HEIGHT;
        double width = Main.SCREEN_WIDTH;

        Image image = new Image(Main.class.getResourceAsStream("images/login.png"));
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(450);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        HBox imageBox = new HBox();
        imageBox.getChildren().add(iv1);
        imageBox.setPadding(new Insets(-300,0,0,-130));
        
        // User name Input
        TextField userInput = new TextField("");
        userInput.setPromptText("Username");
        userInput.setMaxWidth(Double.MAX_VALUE);

        // Password Input
        TextField passInput = new TextField("");
        passInput.setPromptText("Password");
        passInput.setMaxWidth(Double.MAX_VALUE);
            
        // Login Action
        Button loginButton = new Button("Log In");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        //loginButton.setOnAction(e -> Main.window.setScene(Main.landing));
        loginButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	Database db = Database.getDatabase();
            	User result = db.loginUser(userInput.getText(), passInput.getText());
            	if (result != null) {
            		Main.setUser(result);
            		Main.landingScreen();
            		Main.storeScreen();
            		Main.settingsScreen();
            	    //Main.leaderboardScreen();
            	    Main.shareScreen();
            		Main.videoSettingsScreen();
            		Main.audioSettingsScreen();
            		Main.accountSettingsScreen();
            		Main.bugReportScreen();
            		
            		String sheet = Main.getSheet();
            		
            		Main.landing.getStylesheets().add(sheet);
            		Main.signUp.getStylesheets().add(sheet);
            	    Main.store.getStylesheets().add(sheet);
            		Main.settings.getStylesheets().add(sheet);
            	    //Main.leaderboard.getStylesheets().add(sheet);
            	    Main.share.getStylesheets().add(sheet);
            		Main.videoSettings.getStylesheets().add(sheet);
            		Main.audioSettings.getStylesheets().add(sheet);
            		Main.accountSettings.getStylesheets().add(sheet);
            		Main.bugReport.getStylesheets().add(sheet);
            		
            		Main.getUser().updateItems();
            		
            		Main.window.setScene(Main.landing);
            		
            	    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            	        public void run() {
            	        	Database db = Database.getDatabase();
            	     	    db.updateUserScore(result);
            	        }
            	    }));
            	}
            	else {
            		System.out.println("Invalid login.");
            	}
            }
          });
        
        // Sign Up Action
        Button signUpButton = new Button("Sign Up");
        signUpButton.setMaxWidth(Double.MAX_VALUE);
        signUpButton.setOnAction(e -> Main.window.setScene(Main.signUp));
        
        // Exit Button
        Button exitButton = new Button("Exit Button");
        exitButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setOnAction(e ->  System.exit(0));
        
        loginBox.setSpacing(10);
        loginBox.setMaxWidth(loginWidth);
        loginBox.getChildren().addAll(userInput, passInput, loginButton, signUpButton, exitButton);
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); 
        userInput.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                loginBox.requestFocus(); 
                firstTime.setValue(false); 
            }
        });
        
        GridPane loginGrid = new GridPane();
        // Puts the login box in the middle-ish part of the screen
        loginGrid.setPadding(new Insets(height/2-100, width/2-100, height/2, width/2-75));
        loginGrid.setVgap(8);
        loginGrid.setHgap(10);
        loginGrid.getChildren().addAll(imageBox, loginBox);
        view.setCenter(loginGrid);
    }
    public Node getView()
    {
        return view;
    }
}
