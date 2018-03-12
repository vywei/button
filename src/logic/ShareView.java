package logic;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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
  private Image facebookImage;
  private Image twitterImage;
  private Image instagramImage;
  
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
    facebookImage = new Image("/logic/images/facebook.png");
    ImageView facebook = new ImageView();
    facebook.setImage(facebookImage);
    facebook.setFitWidth(70);
    facebook.setPreserveRatio(true);
    facebook.setSmooth(true);
    facebook.setCache(true);    
    
    twitterImage = new Image("/logic/images/twitter.png");
    ImageView twitter = new ImageView();
    twitter.setImage(twitterImage);
    twitter.setFitWidth(70);
    twitter.setPreserveRatio(true);
    twitter.setSmooth(true);
    twitter.setCache(true);
    
    instagramImage = new Image("/logic/images/instagram.png");
    ImageView instagram = new ImageView();
    instagram.setImage(instagramImage);
    instagram.setFitWidth(70);
    instagram.setPreserveRatio(true);
    instagram.setSmooth(true);
    instagram.setCache(true);
    
    HBox imageBox = new HBox();
    imageBox.getChildren().addAll(facebook, twitter, instagram);
    imageBox.setPadding(new Insets(130,0,0,0));
    imageBox.setMinWidth(450);
    imageBox.setMaxWidth(Double.MAX_VALUE);
    imageBox.setAlignment(Pos.CENTER);
    imageBox.setSpacing(30);
  
    return imageBox;
  }
  
  
}
