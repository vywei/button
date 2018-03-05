package logic;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

  static Stage window;

  protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      
  protected static Scene login;
  protected static Scene home;
  protected static Scene createUser;
 
  protected static Scene settings;
  protected static Scene account;
  protected static Scene email;
  protected static Scene password;
  
  protected static String pString = "Password";
  protected static String uString = "Username";
  protected static String cancelString = "Cancel";
  
  protected static User user; 
  protected static ArrayList<User> users;
  static String s; 
  
  public static Shop theShop = new Shop();
  
  private static void setup(Stage prim, String sheet){
    s = sheet;
    window = prim;
    window.setTitle("Button");

    loginScreen();

    window.setScene(login);
    window.show();
}
  
  public void start(Stage primaryStage) throws Exception{
      String styleSheet = getClass().getResource("theme.css").toExternalForm(); 
      setup(primaryStage, styleSheet);
  }
  
  public static void main(String[] args) {
    launch(args);
}
  
  private static GridPane gridSetup(Node v){

    GridPane grid = new GridPane();
    
    BorderPane root = new BorderPane();
    root.setCenter(v);
    grid.getChildren().addAll(root);
    return grid;
}
  
  private static void loginScreen()
  {
      LoginView loginView = new LoginView();

      
      GridPane loginGrid = gridSetup(loginView.getView());
      
      
      login = new Scene(loginGrid, screenSize.getWidth(), screenSize.getHeight());
  }

}
