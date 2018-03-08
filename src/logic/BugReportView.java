package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

public class BugReportView {
	private static String t = "theme.css"; 
    private final BorderPane view;
    protected static Scene videoSettings;
     
    public BugReportView()
    {
    	Label header = new Label("Report A Bug");
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
        TextField userInput = new TextField("");
        userInput.setPromptText("Email");
        userInput.setMaxWidth(Double.MAX_VALUE);
        
        TextField bugRep = new TextField("");
        bugRep.setPromptText("Bug Report");
        bugRep.setMinHeight(150);
        bugRep.setMaxWidth(Double.MAX_VALUE);
        
        Button submitButton = new Button("Submit");
        submitButton.setMaxWidth(Double.MAX_VALUE);
        submitButton.setPadding(new Insets(10, 0, 10, 0));
        submitButton.setOnAction(e -> Main.window.setScene(Main.bugReport));

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(40);
        vbButtons.setPadding(new Insets(30, 30, 30, 30));
        vbButtons.setMinWidth(450);
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.getChildren().addAll(header, userInput,bugRep,submitButton);
        
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
