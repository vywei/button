package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class LandingView 
{
    private static String t = "theme.css"; 
    private final BorderPane view;
    
    public LandingView()
    {

       Sidebar sidebar = new Sidebar();
       double buttonWidth = 150;
       Button add = new Button("Button");
       add.setMinWidth(buttonWidth);

      // The title text on top and its alignment
       Label header = new Label("Welcome to Home, " + Main.user.getUsername());
       header.setMaxWidth(Double.MAX_VALUE);
       header.setAlignment(Pos.CENTER);

       GridPane homeGrid = new GridPane();
       homeGrid.setPadding(new Insets(0, 0, 0, 0));
       homeGrid.setVgap(8);
       homeGrid.setHgap(10);
       homeGrid.getStylesheets().add(getClass().getResource(t).toExternalForm());
       homeGrid.getStyleClass().add("root");
       
       
       BorderPane root = new BorderPane();
       root.setRight(sidebar);
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
