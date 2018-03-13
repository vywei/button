package logic;


import java.util.logging.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class Sidebar extends VBox implements Observer
{
    private Text scoreLabel;
    private Text score;
    private User user;
    private static final Logger LOGGER = Logger.getLogger(Sidebar.class.getName());
    
    public Sidebar(User user)
    {
        this.user = user;

        int buttonWidth = 500;
        
        //Score Box TextField
        scoreLabel = new Text ("SCORE");
        score = new Text (Integer.toString(user.getCurrentScore()));
        
        scoreLabel.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD,26));
        scoreLabel.setFill(Color.WHITE);
        score.setFont(Font.font("Verdana", FontWeight.BOLD,30));
        score.setFill(Color.WHITE);
        
        Rectangle rect = new Rectangle();
        rect.getStyleClass().add("my-rect");

        VBox scoreBox = new VBox();
        scoreBox.getChildren().addAll(scoreLabel, score);
        scoreBox.setSpacing(5); 
        scoreBox.setPadding(new Insets(0,0,20,0));
        scoreBox.setAlignment(Pos.CENTER);
        
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
        this.setSpacing(20);
        
        this.setMaxWidth(buttonWidth);
        this.setPadding(new Insets(20,20,10,10));
        this.getChildren().addAll(scoreBox, homeButton, storeButton, settingsButton, shareButton, leaderboardButton, logoutButton);   
        this.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        this.getStyleClass().add("root");
    }

    @Override
    public void update() {
      LOGGER.info("^");
      score.setText(Integer.toString(user.getCurrentScore()));
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
