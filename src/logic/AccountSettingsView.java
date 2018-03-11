package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.control.TextField;


public class AccountSettingsView {
	private static String t = "theme.css"; 
    private final BorderPane view;
    protected static Scene videoSettings;
    
   public Node getView()
   {
       view.getStylesheets().add(getClass().getResource(t).toExternalForm());
       view.getStyleClass().add("root");
       return view;
   }
    public AccountSettingsView()
    {
    	Label header = new Label("Link Accounts");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);
        header.getStylesheets().add(getClass().getResource(t).toExternalForm());
        header.getStyleClass().add("a-header"); 
    	
        GridPane homeGrid = new GridPane();
        homeGrid.setPadding(new Insets(0, 0, 0, 0));
        homeGrid.setVgap(8);
        homeGrid.setHgap(10);
        homeGrid.getStylesheets().add(getClass().getResource(t).toExternalForm());
        homeGrid.getStyleClass().add("root");

        User temp = Main.getUser();
        
        Sidebar sidebar = new Sidebar(temp);
        temp.register((Observer) sidebar);
       
        HBox settingsBox = new HBox();
        settingsBox.setPadding(new Insets(0,0,0,0));
        settingsBox.setMinWidth(450);
        settingsBox.setAlignment(Pos.CENTER);
        
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));
        
        // User name Input
      // Label facebookLabel = new Label("Facebook:");
        TextField userInput = new TextField("");
        userInput.setPromptText("Facebook Email");
        TextField pass = new PasswordField();
        pass.setPromptText("Facebook Password");
        Button linkFbButton = new Button("Link Facebook");
        
      //  Label twitterLabel = new Label("Twitter:");
        TextField userInputTwitter = new TextField("");
        userInputTwitter.setPromptText("Twitter Email");
        TextField passTwitter = new PasswordField();
        passTwitter.setPromptText("Twitter Password");
        Button linkTwitterButton = new Button("Link Twitter");
        
     //   Label instaLabel = new Label("Instagram:");
        TextField userInputInsta = new TextField("");
        userInputInsta.setPromptText("Instagram Email");
        TextField passInsta = new PasswordField();
        passInsta.setPromptText("Instagram Password");
        Button linkInstaButton = new Button("Link Instagram");
        
        TextField bugRep = new TextField("");
        bugRep.setPromptText("Bug Report");
        bugRep.setMinHeight(150);
        bugRep.setMaxWidth(Double.MAX_VALUE);
        
        Button submitButton = new Button("Submit");
        submitButton.setMaxWidth(Double.MAX_VALUE);
        submitButton.setPadding(new Insets(10, 0, 10, 0));
        submitButton.setOnAction(e -> Main.window.setScene(Main.bugReport));

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(30, 30, 30, 30));
        vbButtons.setMinWidth(450);
        vbButtons.setAlignment(Pos.TOP_LEFT);
        vbButtons.getChildren().addAll(header, userInput,pass,linkFbButton,userInputTwitter,passTwitter,linkTwitterButton,userInputInsta,passInsta,linkInstaButton);
        
        settingsBox.getChildren().addAll(vbButtons);

        BorderPane root = new BorderPane();
        root.setRight(sidebar);
        root.setLeft(settingsBox);
        root.getStylesheets().add(getClass().getResource(t).toExternalForm());
        root.getStyleClass().add("root");

        homeGrid.getChildren().addAll(root);
       
        view = root;
    }

}
