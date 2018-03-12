package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;


public class LandingView 
{
    private static String t = "theme.css"; 
    private final BorderPane view;
    private Image unpressedImage;
    private Image pressedImage;
    
    public LandingView()
    {
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
          
        Btn button = new Btn(Main.getUser().getCurrentSkin(), 1, null);
              
        double buttonWidth = 150;
        Button add = new Button("Button");
        add.setMinWidth(buttonWidth);
        User temp = Main.getUser();
        
        Sidebar sidebar = new Sidebar(temp);
        temp.register((Observer) sidebar);
        temp.register((Observer) button);
        
        System.out.println("*" + temp.getObservers().size());

        unpressedImage = new Image(Main.class.getResourceAsStream(button.getCurrentSkin().getImage()));
        pressedImage = new Image(Main.class.getResourceAsStream(button.getCurrentSkin().getImagePressed()));
        ImageView iv1 = new ImageView();
        iv1.setImage(unpressedImage);
        iv1.setFitWidth(450);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
        HBox imageBox = new HBox();
        imageBox.getChildren().add(iv1);
        imageBox.setPadding(new Insets(0,0,0,0));
        
        iv1.setOnMousePressed(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            iv1.setImage(pressedImage);
            button.increaseScore();
          }
        });
        iv1.setOnMouseReleased(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            iv1.setImage(unpressedImage);
          }
        });
        
        
        
        BorderPane root = new BorderPane();
        root.setRight(sidebar);
        root.setLeft(imageBox);
        root.getStylesheets().add(getClass().getResource(t).toExternalForm());
        root.getStyleClass().add("root");

        homeGrid.getChildren().addAll(root);
       
        view = root;
    }
    
    public void updateImages() {
    	String unpressedPath = Main.getUser().getCurrentSkin().getImage();
    	unpressedImage = new Image(Main.class.getResourceAsStream(unpressedPath));
    	String pressedPath = Main.getUser().getCurrentSkin().getImagePressed();
    	pressedImage = new Image(Main.class.getResourceAsStream(pressedPath));
    }
    
   public void prepareView() {
	   
   }
    
   public Node getView()
   {
       view.getStylesheets().add(getClass().getResource(t).toExternalForm());
       view.getStyleClass().add("root");
       return view;
   }

}
