package logic;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

  static Stage window;

  protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  public static final int SCREEN_WIDTH = 575;
  public static final int SCREEN_HEIGHT = 440;
  protected static Scene login;
  protected static Scene landing;
  protected static Scene store;
  protected static Scene signUp;
  protected static Scene settings;
  protected static Scene account;
  protected static Scene share;
  protected static Scene leaderboard;
  protected static Scene videoSettings;
  protected static Scene audioSettings;
  protected static Scene accountSettings;
  protected static Scene linkAccounts;
  protected static Scene bugReport;
  protected static Scene credits;
  protected static Scene easter;
  protected static LandingView landingView;

  protected static String pString = "Password";
  protected static String uString = "Username";
  protected static String cancelString = "Cancel";
  
  protected static User user; 
  protected static ArrayList<User> users;
  protected static Database db;
  static String s; 
  
  private static Shop theShop = new Shop();
  
  private static void setup(Stage prim, String sheet){
    s = sheet;
    window = prim;
    window.setTitle("Button");
    
    db = Database.getDatabase();

    loginScreen();
    signUpScreen();
    
    login.getStylesheets().add(sheet);
    signUp.getStylesheets().add(sheet);

    window.setScene(login);
    window.show();
}
  
  public void start(Stage primaryStage) throws Exception{
      String styleSheet = getClass().getResource("theme.css").toExternalForm(); 
      setup(primaryStage, styleSheet);
  }
  
  public static Shop getShop() {
	  return theShop;
  }
  
  public static void refreshShop() {
	  theShop = new Shop();
  }
  
  public static void main(String[] args) {
    launch(args);
  }
  
  public static User getUser() {
    return user;
  }
  
  public static String getSheet() {
	 return s;
  }
  
  public static void setUser(User u) {
	  user = u;
  }
  
  public static GridPane gridSetup(Node v){

    GridPane grid = new GridPane();
    
    BorderPane root = new BorderPane();
    root.setCenter(v);
    grid.getChildren().addAll(root);
    return grid;
}
  
  public static void loginScreen()
  {
      LoginView loginView = new LoginView();

      GridPane loginGrid = gridSetup(loginView.getView());
      
      login = new Scene(loginGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  public static void signUpScreen()
  {
      SignUpView signUpView = new SignUpView();

      GridPane signUpGrid = gridSetup(signUpView.getView());
      
      signUp = new Scene(signUpGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  public static void easterEggScreen()
  {
      EasterEgg easterView = new EasterEgg();

      GridPane easterGrid = gridSetup(easterView.getView());
      
      easter = new Scene(easterGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
      
  public static void landingScreen()
  {
      landingView  = new LandingView();

      GridPane landingGrid = gridSetup(landingView.getView());
      
      landing = new Scene(landingGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  public static void storeScreen()
  {
      ShopView storeView = new ShopView();

      GridPane storeGrid = gridSetup(storeView.getView());
      
      store = new Scene(storeGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  public static void settingsScreen()
  {
      SettingsView settingsView = new SettingsView();

      GridPane settingsGrid = gridSetup(settingsView.getView());
      
      settings = new Scene(settingsGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  public static void leaderboardScreen()
  {
      LeaderboardView leaderboardView = new LeaderboardView();

      GridPane leaderboardGrid = gridSetup(leaderboardView.getView());
      
      leaderboard = new Scene(leaderboardGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  public static void shareScreen()
  {
      ShareView shareView = new ShareView();

      GridPane shareGrid = gridSetup(shareView.getView());
      
      share = new Scene(shareGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  public static void videoSettingsScreen()
  {
      VideoSettingsView videoSettingsView = new VideoSettingsView();

      GridPane videoSettingsGrid = gridSetup(videoSettingsView.getView());
      
      videoSettings = new Scene(videoSettingsGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  public static void audioSettingsScreen()
  {
      AudioSettingsView audioSettingsView = new AudioSettingsView();

      GridPane audioSettingsGrid = gridSetup(audioSettingsView.getView());
      
      audioSettings = new Scene(audioSettingsGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  public static void accountSettingsScreen()
  {
      AccountSettingsView accountSettingsView = new AccountSettingsView();

      GridPane accountSettingsGrid = gridSetup(accountSettingsView.getView());
      
      accountSettings = new Scene(accountSettingsGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  public static void bugReportScreen()
  {
      BugReportView bugReportView = new BugReportView();

      GridPane bugReportGrid = gridSetup(bugReportView.getView());
      
      bugReport = new Scene(bugReportGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  public static void creditsScreen()
  {
      CreditsView creditsView = new CreditsView();

      GridPane creditsGrid = gridSetup(creditsView.getView());
      
      credits = new Scene(creditsGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
}
