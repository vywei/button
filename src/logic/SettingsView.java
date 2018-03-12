package logic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsView 
{
	private static String t = "theme.css"; 
    private final BorderPane view;
    protected static Scene videoSettings;
     
    public SettingsView()
    {

    	    Label header = new Label("Settings");
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
        
        Button videoButton = new Button("Video");
        videoButton.setMaxWidth(Double.MAX_VALUE);
        videoButton.setPadding(new Insets(10, 0, 10, 0));
        videoButton.setOnAction(e -> Main.window.setScene(Main.videoSettings));
        
        Button audioButton = new Button("Audio");
        audioButton.setMaxWidth(Double.MAX_VALUE);
        audioButton.setPadding(new Insets(10, 0, 10, 0));
        audioButton.setOnAction(e -> Main.window.setScene(Main.audioSettings));
        
        Button accountsButton = new Button("Link Accounts");
        accountsButton.setMaxWidth(Double.MAX_VALUE); 
        accountsButton.setPadding(new Insets(10, 0, 10, 0));
        accountsButton.setOnAction(e -> Main.window.setScene(Main.accountSettings));
        
        Button bugButton = new Button("Report a Bug");
        bugButton.setMaxWidth(Double.MAX_VALUE);  
        bugButton.setPadding(new Insets(10, 0, 10, 0));
        //bugButton.setOnAction(e -> Main.window.setScene(Main.landing));
        bugButton.setOnAction(e -> 
        {
          Main.window.setScene(Main.bugReport);
        });
        bugButton.setMaxWidth(Double.MAX_VALUE);
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(40);
        vbButtons.setPadding(new Insets(30, 30, 30, 30));
        vbButtons.setMinWidth(450);
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.getChildren().addAll(header, videoButton, audioButton, accountsButton, bugButton);
        
        settingsBox.getChildren().addAll(vbButtons);

        BorderPane root = new BorderPane();
        root.setRight(sidebar);
        root.setLeft(settingsBox);
        root.getStylesheets().add(getClass().getResource(t).toExternalForm());
        root.getStyleClass().add("root");

        homeGrid.getChildren().addAll(root);
       
        view = root;
        
    }
    
   public Node getView()
   {
       view.getStylesheets().add(getClass().getResource(t).toExternalForm());
       view.getStyleClass().add("root");
       return view;
   }
}
