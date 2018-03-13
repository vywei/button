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
	
    private final BorderPane view;
    protected static Scene videoSettings;
    private static String t = "theme.css"; 
    

    public AccountSettingsView()
    {
    	Label head = new Label("Link Accounts");
        head.setMaxWidth(Double.MAX_VALUE);
        head.setAlignment(Pos.CENTER);
        head.getStylesheets().add(getClass().getResource(t).toExternalForm());
        head.getStyleClass().add("a-header"); 
    	
        GridPane hGrid = new GridPane();
        hGrid.setPadding(new Insets(0, 0, 0, 0));
        hGrid.setVgap(8);
        hGrid.setHgap(10);
        hGrid.getStylesheets().add(getClass().getResource(t).toExternalForm());
        hGrid.getStyleClass().add("root");

        User tempUser = Main.getUser();
        
        Sidebar sideBar = new Sidebar(tempUser);
        tempUser.register((Observer) sideBar);
       
        HBox sBox = new HBox();
        sBox.setPadding(new Insets(0,0,0,0));
        sBox.setMinWidth(450);
        sBox.setAlignment(Pos.CENTER);
        
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));
       
        TextField userInput = new TextField("");
        userInput.setPromptText("Facebook Email");
        TextField pass = new PasswordField();
        pass.setPromptText("Facebook Password");
        Btn linkFbButton = new Btn(null, 1, "Link Facebook");
        
     
        TextField userInputTwitter = new TextField("");
        userInputTwitter.setPromptText("Twitter Email");
        TextField passTwitter = new PasswordField();
        passTwitter.setPromptText("Twitter Password");
        Btn linkTwitterButton = new Btn(null, 1, "Link Twitter");
        

        TextField userInputInsta = new TextField("");
        userInputInsta.setPromptText("Instagram Email");
        TextField passInsta = new PasswordField();
        passInsta.setPromptText("Instagram Password");
        Btn linkInstaButton = new Btn(null, 1, "Link Instagram");
        
        TextField bugRep = new TextField("");
        bugRep.setPromptText("Bug Report");
        bugRep.setMinHeight(150);
        bugRep.setMaxWidth(Double.MAX_VALUE);
        
        Btn submitButton = new Btn(null, 1, "Submit");
        submitButton.setMaxWidth(Double.MAX_VALUE);
        submitButton.setPadding(new Insets(10, 0, 10, 0));
        submitButton.setOnAction(e -> 
        {
          Main.window.setScene(Main.bugReport);
          submitButton.increaseScore();
        }
        );

        VBox vButtons = new VBox();
        vButtons.setSpacing(10);
        vButtons.setPadding(new Insets(30, 30, 30, 30));
        vButtons.setMinWidth(450);
        vButtons.setAlignment(Pos.TOP_LEFT);
        vButtons.getChildren().addAll(head, userInput,pass,linkFbButton,userInputTwitter,passTwitter,linkTwitterButton,userInputInsta,passInsta,linkInstaButton);
        
        sBox.getChildren().addAll(vButtons);

        BorderPane rootPane = new BorderPane();
        rootPane.setRight(sideBar);
        rootPane.setLeft(sBox);
        rootPane.getStylesheets().add(getClass().getResource(t).toExternalForm());
        rootPane.getStyleClass().add("root");

        hGrid.getChildren().addAll(rootPane);
       
        view = rootPane;
    }
    public Node getView()
    {
        view.getStylesheets().add(getClass().getResource(t).toExternalForm());
        view.getStyleClass().add("rootPane");
        return view;
    }

}
