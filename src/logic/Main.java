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
  protected static Scene store;
  protected static Scene signUp;
  protected static Scene settings;
  protected static Scene account;
  protected static Scene share;
  protected static Scene leaderboard;

  protected static String pString = "Password";
  protected static String uString = "Username";
  protected static String cancelString = "Cancel";
  
  protected static User user; 
  protected static ArrayList<User> users;
  static String s; 
  
  
  private static void setup(Stage prim, String sheet){
    s = sheet;
    window = prim;
    window.setTitle("Button");

    loginScreen();
    user = new User(uString,pString);

    homeScreen();
    signUpScreen();
    
    login.getStylesheets().add(sheet);
    //home.getStylesheets().add(sheet);
    signUp.getStylesheets().add(sheet);
    
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
  
  public static User getUser() {
    return user;
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
  
  private static void homeScreen()
  {
      LandingView landingView = new LandingView();

      GridPane homeGrid = gridSetup(landingView.getView());
      
      home = new Scene(homeGrid, screenSize.getWidth(), screenSize.getHeight());
  }
  
  private static void signUpScreen()
  {
      SignUpView signUpView = new SignUpView();

      GridPane signUpGrid = gridSetup(signUpView.getView());
      
      signUp = new Scene(signUpGrid, screenSize.getWidth(), screenSize.getHeight());
  }

}
