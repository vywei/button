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

    landingScreen();
    signUpScreen();
    //storeScreen();
    settingsScreen();
    //leaderboardScreen();
    //shareScreen();
    videoSettingsScreen();
    audioSettingsScreen();
    accountSettingsScreen();
    bugReportScreen();
    
    login.getStylesheets().add(sheet);
    landing.getStylesheets().add(sheet);
    signUp.getStylesheets().add(sheet);
    //store.getStylesheets().add(sheet);
    settings.getStylesheets().add(sheet);
    //leaderboard.getStylesheets().add(sheet);
    //share.getStylesheets().add(sheet);
    videoSettings.getStylesheets().add(sheet);
    audioSettings.getStylesheets().add(sheet);
    accountSettings.getStylesheets().add(sheet);
    bugReport.getStylesheets().add(sheet);

    
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
      
      login = new Scene(loginGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  private static void signUpScreen()
  {
      SignUpView signUpView = new SignUpView();

      GridPane signUpGrid = gridSetup(signUpView.getView());
      
      signUp = new Scene(signUpGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
      
  private static void landingScreen()
  {
      LandingView landingView = new LandingView();

      GridPane landingGrid = gridSetup(landingView.getView());
      
      landing = new Scene(landingGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  /*private static void storeScreen()
  {
      StoreView storeView = new StoreView();

      GridPane storeGrid = gridSetup(storeView.getView());
      
      store new Scene(storeGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }*/
  
  private static void settingsScreen()
  {
      SettingsView settingsView = new SettingsView();

      GridPane settingsGrid = gridSetup(settingsView.getView());
      
      settings = new Scene(settingsGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  /*private static void leaderboardScreen()
  {
      LeaderboardView leaderboardView = new LeaderboardView();

      GridPane leaderboardGrid = gridSetup(leaderboardView.getView());
      
      leaderboard = new Scene(leaderboardGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
    private static void shareScreen()
  {
      ShareView shareView = new ShareView();

      GridPane shareGrid = gridSetup(shareView.getView());
      
      share = new Scene(shareGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  */
  private static void videoSettingsScreen()
  {
      VideoSettingsView videoSettingsView = new VideoSettingsView();

      GridPane videoSettingsGrid = gridSetup(videoSettingsView.getView());
      
      videoSettings = new Scene(videoSettingsGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  private static void audioSettingsScreen()
  {
      AudioSettingsView audioSettingsView = new AudioSettingsView();

      GridPane audioSettingsGrid = gridSetup(audioSettingsView.getView());
      
      audioSettings = new Scene(audioSettingsGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  private static void accountSettingsScreen()
  {
      AccountSettingsView accountSettingsView = new AccountSettingsView();

      GridPane accountSettingsGrid = gridSetup(accountSettingsView.getView());
      
      accountSettings = new Scene(accountSettingsGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  
  private static void bugReportScreen()
  {
      BugReportView bugReportView = new BugReportView();

      GridPane bugReportGrid = gridSetup(bugReportView.getView());
      
      bugReport = new Scene(bugReportGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }
  /*private static void linkAccountsScreen()
  {
      LinkAccountsView linkAccountsView = new LinkAccountsView();

      GridPane linkAccountsGrid = gridSetup(linkAccountsView.getView());
      
      bugReport = new Scene(linkAccountsGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
  }*/
}
