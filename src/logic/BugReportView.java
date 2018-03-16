package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.control.TextField;


public class BugReportView {
	private static String t = "theme.css"; 
    private final BorderPane view;
    protected static Scene videoSettings;
    public Node getView()
    {
        view.getStylesheets().add(getClass().getResource(t).toExternalForm());
        view.getStyleClass().add("root");
        return view;
    }
    public BugReportView()
    {
    	Label h = new Label("Report A Bug");
        h.setMaxWidth(Double.MAX_VALUE);
        h.setAlignment(Pos.CENTER);
        h.getStylesheets().add(getClass().getResource(t).toExternalForm());
        h.getStyleClass().add("a-header"); 
    	
        GridPane hGrid1 = new GridPane();
        hGrid1.setPadding(new Insets(0, 0, 0, 0));
        hGrid1.setVgap(8);
        hGrid1.setHgap(10);
        hGrid1.getStylesheets().add(getClass().getResource(t).toExternalForm());
        hGrid1.getStyleClass().add("root");

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
        
        Btn submitButton = new Btn(null, 1, "Submit");
        submitButton.setPadding(new Insets(10, 0, 10, 0));
        submitButton.setMaxWidth(Double.MAX_VALUE);
        submitButton.setOnAction(e ->
        {
          submitButton.increaseScore();
          BugReport br = new BugReport(Main.getUser(), userInput.getText(), bugRep.getText());
          Main.db.submitBugReport(br);
          userInput.setText("");
          bugRep.setText("");
          Main.window.setScene(Main.bugReport);
        }
        );

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(40);
        vbButtons.setPadding(new Insets(30, 30, 30, 30));
        vbButtons.setMinWidth(450);
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.getChildren().addAll(h, userInput,bugRep,submitButton);
        
        settingsBox.getChildren().addAll(vbButtons);

        BorderPane root = new BorderPane();
        root.setRight(sidebar);
        root.setLeft(settingsBox);
        root.getStylesheets().add(getClass().getResource(t).toExternalForm());
        root.getStyleClass().add("root");

        hGrid1.getChildren().addAll(root);
       
        view = root;
    }
    

}
