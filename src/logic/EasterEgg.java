package logic;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EasterEgg 
{
    private static String t = "theme.css"; 
    private final BorderPane view;
    protected static Scene store;
    boolean purchaseView = true;
    private VBox body;
     
    public EasterEgg()
    {    
        User temp = Main.getUser();
      
        Sidebar sidebar = new Sidebar(temp);
        temp.register((Observer) sidebar);
        
        VBox root = new VBox();
        root.setMaxWidth(Double.MAX_VALUE);
        root.setMinWidth(450);
        root.getChildren().addAll(getHeader(), getBody());
        
        BorderPane pane = new BorderPane();
        pane.setRight(sidebar);
        pane.setLeft(root);
        pane.getStylesheets().add(getClass().getResource(t).toExternalForm());
        pane.getStyleClass().add("root");
        
        view = pane;
    }
    
   public Node getView()
   {
       view.getStylesheets().add(getClass().getResource(t).toExternalForm());
       view.getStyleClass().add("root");
       return view;
   }
   
   private HBox getHeader() 
   {
     Label title = new Label("CONGRATS");
     title.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD,14));
     title.getStylesheets().add(getClass().getResource(t).toExternalForm());
     title.getStyleClass().add("a-header"); 
     
     HBox header = new HBox();
     header.getChildren().add(title);
     header.setAlignment(Pos.CENTER);
     return header;
     
   }
   
   private VBox getBody() 
   {
     body = new VBox();
     
     body.setAlignment(Pos.CENTER);
     body.setPrefHeight(Main.SCREEN_HEIGHT);
     body.setPrefWidth(450);
     body.setPadding(new Insets(10,0,0,0));
     
     VBox core = new VBox();
     
     Label des = new Label("YOU REACHED THE REQUIRED LINES OF CODE");
     Label des2 = new Label("PLEASE ENJOY THIS BONUS BUTTON FOR A 5 TIME SCORE MULTIPLYER");
     Label des3 = new Label("DONT LEAVE THIS PAGE OR YOU CAN'T GET BACK >:D");
     
     des.setFont(Font.font(12));
     des.setTextFill(Color.WHITE);
     des.setPadding(new Insets(5,0,0,0));
     
     des2.setFont(Font.font(12));
     des2.setTextFill(Color.WHITE);
     des2.setPadding(new Insets(5,0,0,0));
     
     des3.setFont(Font.font(12));
     des3.setTextFill(Color.WHITE);
     des3.setPadding(new Insets(5,0,0,0));
     
     core.getChildren().add(des);
     core.getChildren().add(des2);
     core.getChildren().add(des3);
     
     core.setBorder(new Border(new BorderStroke(Color.BLACK, 
         BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
     
     Btn bonus = new Btn(null, 5, "CLICK ME");
     
     bonus.setOnAction((ActionEvent event) ->
     {
         bonus.increaseScore();
     }
     );
     
     body.getChildren().add(core);
     body.getChildren().add(bonus);
     
     return body;
   }
}