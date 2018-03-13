package logic;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ShareView {
  
  private static String t = "theme.css"; 
  private final BorderPane view;
  protected static Scene share;
  private static final String completeImage = "/logic/images/6156-200.png";
  
  public ShareView()
  {
    User temp = Main.getUser();
    
    Sidebar sidebar = new Sidebar(temp);
    temp.register((Observer) sidebar);
    
    VBox left = new VBox();
    left.getChildren().addAll(getHeader(), getBody());
    
    
    BorderPane root = new BorderPane();
    root.setRight(sidebar);
    root.setLeft(left);
    root.getStylesheets().add(getClass().getResource(t).toExternalForm());
    root.getStyleClass().add("root");
    
    view = root;
    
  }
  public Node getView()
  {
    view.getStylesheets().add(getClass().getResource(t).toExternalForm());
    view.getStyleClass().add("root");
    return view;
  }
  
  private HBox getHeader()
  {
    Label title = new Label("Share");
    title.getStylesheets().add(getClass().getResource(t).toExternalForm());
    title.getStyleClass().add("a-header");
    
    HBox header = new HBox();
    header.getChildren().add(title);
    header.setAlignment(Pos.CENTER);
    header.setPadding(new Insets(20,0,0,0));
    return header;
  }
  
  private HBox getBody() 
  {
	Image facebookImage;
	Image twitterImage;
	Image instagramImage;
	Image sharedImage;
	Image sharedImage2;
	Image sharedImage3;
	  
    facebookImage = new Image("/logic/images/facebook.png");
    ImageView facebook = new ImageView();
    facebook.setImage(facebookImage);
    facebook.setFitWidth(70);
    facebook.setPreserveRatio(true);
    facebook.setSmooth(true);
    facebook.setCache(true);  
    
    sharedImage = new Image(completeImage);
    ImageView shared = new ImageView();
    shared.setImage(sharedImage);
    shared.setFitWidth(70);
    shared.setPreserveRatio(true);
    shared.setSmooth(true);
    shared.setCache(true);  
    
    sharedImage2 = new Image(completeImage);
    ImageView shared2 = new ImageView();
    shared2.setImage(sharedImage2);
    shared2.setFitWidth(70);
    shared2.setPreserveRatio(true);
    shared2.setSmooth(true);
    shared2.setCache(true);
    
    Button facebookB = new Button("",facebook);
    facebookB.setOnAction((ActionEvent e)-> {
            //Button button = (Button) e.getSource();
            facebookB.setGraphic(shared2);
    });
    
    twitterImage = new Image("/logic/images/twitter.png");
    ImageView twitter = new ImageView();
    twitter.setImage(twitterImage);
    twitter.setFitWidth(70);
    twitter.setPreserveRatio(true);
    twitter.setSmooth(true);
    twitter.setCache(true);
    
    sharedImage3 = new Image(completeImage);
    ImageView shared3 = new ImageView();
    shared3.setImage(sharedImage3);
    shared3.setFitWidth(70);
    shared3.setPreserveRatio(true);
    shared3.setSmooth(true);
    shared3.setCache(true);
    
    Button twitterB = new Button("",twitter);
    twitterB.setOnAction((ActionEvent e)-> {
            Button button = (Button) e.getSource();
            button.setGraphic(shared3);
    });
    
    instagramImage = new Image("/logic/images/instagram.png");
    ImageView instagram = new ImageView();
    instagram.setImage(instagramImage);
    instagram.setFitWidth(70);
    instagram.setPreserveRatio(true);
    instagram.setSmooth(true);
    instagram.setCache(true);
    
    Button instagramB = new Button("",instagram);
    instagramB.setOnAction((ActionEvent e)->  {
            Button button = (Button) e.getSource();
            button.setGraphic(shared);
    });
    
    HBox imageBox = new HBox();
    imageBox.getChildren().addAll(facebookB, twitterB, instagramB);
    imageBox.setPadding(new Insets(130,0,0,0));
    imageBox.setMinWidth(450);
    imageBox.setMaxWidth(Double.MAX_VALUE);
    imageBox.setAlignment(Pos.CENTER);
    imageBox.setSpacing(30);
  
    return imageBox;
  }
  
  
}
