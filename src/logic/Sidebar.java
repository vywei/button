package logic;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox
{
    public Sidebar()
    {
        int score = 0;
        int buttonWidth = 500;
        
        // Logout Button
        //Button logoutButton = new Button("Log Out");
        //logoutButton.setOnAction(e -> Main.window.setScene(Main.login));
        //logoutButton.setMaxWidth(Double.MAX_VALUE);
        
        //Score Box TextField
        Label scoreLabel = new Label("SCORE:");
        Text text = new Text (Integer.toString(score));
        HBox scoreBox = new HBox();
        scoreBox.getChildren().addAll(scoreLabel, text);
        scoreBox.setSpacing(20); 
        scoreBox.setPadding(new Insets(0,0,70,0));
        
        // Home Back Button
        Button homeButton = new Button("Home");
        homeButton.setOnAction(e -> 
        {
            Main.window.setScene(Main.home);    
        });
        homeButton.setMaxWidth(Double.MAX_VALUE);

        // Add Store
        Button storeButton = new Button("Store");
        storeButton.setOnAction(e -> 
        {
          Main.window.setScene(Main.store);
        });
        storeButton.setMaxWidth(Double.MAX_VALUE);
        
        // Add Settings
        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(e -> 
        {
          Main.window.setScene(Main.settings);
        });
        settingsButton.setMaxWidth(Double.MAX_VALUE);
        
        //Add Share
        Button shareButton = new Button("Share");
        shareButton.setOnAction(e -> 
        {
          Main.window.setScene(Main.share);
        });
        shareButton.setMaxWidth(Double.MAX_VALUE);

        // Add Leaderboard
        Button leaderboardButton = new Button("Leaderboard");
        leaderboardButton.setOnAction(e -> 
        {
          Main.window.setScene(Main.leaderboard);
        });
        leaderboardButton.setMaxWidth(Double.MAX_VALUE);
        
        // Populating the nav bar
        this.setSpacing(30);
        
        this.setMaxWidth(buttonWidth);
        this.setPadding(new Insets(20,20,10,10));
        this.getChildren().addAll(scoreBox, homeButton, storeButton, settingsButton, shareButton, leaderboardButton);   
        this.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        this.getStyleClass().add("root");
    }   
}
