package logic;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox
{
    public Sidebar()
    {

        int buttonWidth = 150;
        
        // Logout Button
        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> Main.window.setScene(Main.login));
        logoutButton.setMaxWidth(Double.MAX_VALUE);
        
        // Home Back Button
        Button homeButton = new Button("Home");
        homeButton.setOnAction(e -> 
        {
            Main.window.setScene(Main.home);    
        });
        homeButton.setMaxWidth(Double.MAX_VALUE);

        // Add Store
        Button calendarButton = new Button("Store");
        //calendarButton.setOnAction(e -> Main.window.setScene(Main.store));
        calendarButton.setMaxWidth(Double.MAX_VALUE);
        
        // Add Settings
        Button assignmentButton = new Button("Settings");
        //assignmentButton.setOnAction(e ->Main.window.setScene(Main.settings));
        assignmentButton.setMaxWidth(Double.MAX_VALUE);
        
  
        //Add Share
        Button scratchpadButton = new Button("Share");
        //scratchpadButton.setOnAction(e -> Main.window.setScene(Main.share));
        scratchpadButton.setMaxWidth(Double.MAX_VALUE);

        // Add Leaderboard
        Button settingsButton = new Button("Leaderboard");
        //settingsButton.setOnAction(e ->Main.window.setScene(Main.leaderboard));
        settingsButton.setMaxWidth(Double.MAX_VALUE);
        
        // Add Exit
        Button exitButton = new Button("Exit Button");
        exitButton.setOnAction(e -> System.exit(0));
        exitButton.setMaxWidth(Double.MAX_VALUE);
        
        // Populating the nav bar
        this.setSpacing(10);
        this.setMaxWidth(buttonWidth);
        this.setPadding(new Insets(20,20,10,10));
        //this.getChildren().addAll(homeButton, storeButton, shareButton);
        this.getChildren().addAll(scratchpadButton, settingsButton,logoutButton, exitButton);   
        this.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        this.getStyleClass().add("root");
    }   
}
