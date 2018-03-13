package logic;


import java.util.logging.Logger;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;



public class Sidebar extends VBox implements Observer
{
    private Text text;
    private User user;
    private static final Logger LOGGER = Logger.getLogger(Sidebar.class.getName());
    
    public Sidebar(User user)
    {
        this.user = user;

        int buttonWidth = 500;
        
        //Score Box TextField
        Label scoreLabel = new Label("SCORE:");
        text = new Text (Integer.toString(user.getCurrentScore()));
        
        VBox scoreBox = new VBox();
        scoreBox.getChildren().addAll(scoreLabel, text);
        scoreBox.setSpacing(20); 
        scoreBox.setPadding(new Insets(0,0,30,0));
        
        // Home Back Button
        Btn homeButton = new Btn(null, 1, "Home");
        homeButton.setOnAction(e -> 
        {
            homeButton.increaseScore();
            Main.window.setScene(Main.landing);    
        });
        homeButton.setMaxWidth(Double.MAX_VALUE);

        // Add Store
        Btn storeButton = new Btn(null, 1, "Store");
        storeButton.setOnAction(e -> 
        {
          storeButton.increaseScore();
          Main.window.setScene(Main.store);
        });
        storeButton.setMaxWidth(Double.MAX_VALUE);
        
        // Add Settings
        Btn settingsButton = new Btn(null, 1, "Settings");
        settingsButton.setOnAction(e -> 
        {
          settingsButton.increaseScore();
          Main.window.setScene(Main.settings);
        });
        settingsButton.setMaxWidth(Double.MAX_VALUE);
        
        //Add Share
        Btn shareButton = new Btn(null, 1, "Share");
        shareButton.setOnAction(e -> 
        {
          shareButton.increaseScore();
          Main.window.setScene(Main.share);
        });
        shareButton.setMaxWidth(Double.MAX_VALUE);

        // Add Leaderboard
        Btn leaderboardButton = new Btn(null, 1, "Leaderboard");
        leaderboardButton.setOnAction(e -> 
        {
          leaderboardButton.increaseScore();
          Main.window.setScene(Main.leaderboard);
        });
        leaderboardButton.setMaxWidth(Double.MAX_VALUE);
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e ->       
          Main.window.setScene(Main.login)
        );
        logoutButton.setMaxWidth(Double.MAX_VALUE);
        
        // Populating the nav bar
        this.setSpacing(25);
        
        this.setMaxWidth(buttonWidth);
        this.setPadding(new Insets(20,20,10,10));
        this.getChildren().addAll(scoreBox, homeButton, storeButton, settingsButton, shareButton, leaderboardButton, logoutButton);   
        this.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        this.getStyleClass().add("root");
    }

    @Override
    public void update() {
      LOGGER.info("^");
      text.setText(Integer.toString(user.getCurrentScore()));
    }

    @Override
    public void update(String type) {
      // No need to do anything here
    }

    @Override
    public void update(int amount) {
      // No need to do anything here
    }   
}
