package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VideoSettingsView 
{
	private static String t = "theme.css"; 
    private final BorderPane view;
     
    public VideoSettingsView()
    {
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));
        
    	Label header = new Label("Video Settings");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);
        header.getStylesheets().add(getClass().getResource(t).toExternalForm());
        header.getStyleClass().add("a-header"); 

        User temp = Main.getUser();
        Settings currentSettings = temp.getSettings();
        
        Sidebar sidebar = new Sidebar(temp);
        temp.register((Observer) sidebar);
       
        HBox settingsBox = new HBox();
        settingsBox.setPadding(new Insets(0,0,0,0));
        settingsBox.setMinWidth(450);
        settingsBox.setAlignment(Pos.CENTER);
        
        GridPane homeGrid = new GridPane();
        homeGrid.setPadding(new Insets(0, 0, 0, 0));
        homeGrid.setVgap(8);
        homeGrid.setHgap(10);
        homeGrid.getStylesheets().add(getClass().getResource(t).toExternalForm());
        homeGrid.getStyleClass().add("root");
        
        HBox resolutionBox = new HBox();
        resolutionBox.setPadding(new Insets(0,0,0,0));
        resolutionBox.setMinWidth(350);
        resolutionBox.setAlignment(Pos.CENTER);
        resolutionBox.setSpacing(40);
        
        Label resLabel = new Label("Resolution:");
        ObservableList<String> resOptions = 
        	    FXCollections.observableArrayList(
    	    		currentSettings.getVideoResWidth() + " x " + currentSettings.getVideoResHeight()
        	    );
        final ComboBox<String> resCB = new ComboBox<>(resOptions);
        resCB.setValue(currentSettings.getVideoResWidth() + " x " + currentSettings.getVideoResHeight());
        	
        resolutionBox.getChildren().addAll(resLabel, resCB);
        
        HBox textureBox = new HBox();
        textureBox.setPadding(new Insets(0,0,0,0));
        textureBox.setMinWidth(350);
        textureBox.setAlignment(Pos.CENTER);
        textureBox.setSpacing(40);
        
        Label texLabel = new Label("Texture Quality:");
        
        final ToggleGroup texGroup = new ToggleGroup();

        RadioButton texRb1 = new RadioButton("High");
        texRb1.setToggleGroup(texGroup);

        RadioButton texRb2 = new RadioButton("Medium");
        texRb2.setToggleGroup(texGroup);
         
        RadioButton texRb3 = new RadioButton("Low");
        texRb3.setToggleGroup(texGroup);
        
        if (currentSettings.getTextureQual() == Settings.HIGH) {
          texRb1.setSelected(true);
        }
        else if (currentSettings.getTextureQual() == Settings.MED) {
          texRb2.setSelected(true);
        }
        else if (currentSettings.getTextureQual() == Settings.LOW) {
          texRb3.setSelected(true);
        }
        
        VBox texGroupContain = new VBox(texRb1, texRb2, texRb3);
        	
        textureBox.getChildren().addAll(texLabel, texGroupContain);
        
        HBox effectsBox = new HBox();
        effectsBox.setPadding(new Insets(0,0,0,0));
        effectsBox.setMinWidth(350);
        effectsBox.setAlignment(Pos.CENTER);
        effectsBox.setSpacing(40);
        
        Label effectsLabel = new Label("Effects Quality:");
        
        final ToggleGroup fxGroup = new ToggleGroup();

        RadioButton fxRb1 = new RadioButton("High");
        fxRb1.setToggleGroup(fxGroup);
        fxRb1.setSelected(true);

        RadioButton fxRb2 = new RadioButton("Medium");
        fxRb2.setToggleGroup(fxGroup);
         
        RadioButton fxRb3 = new RadioButton("Low");
        fxRb3.setToggleGroup(fxGroup);
        
        if (currentSettings.getEffectsQual() == Settings.HIGH) {
          fxRb1.setSelected(true);
        }
        else if (currentSettings.getEffectsQual() == Settings.MED) {
          fxRb2.setSelected(true);
        }
        else if (currentSettings.getEffectsQual() == Settings.LOW) {
          fxRb3.setSelected(true);
        }
        
        VBox effectsGroupContain = new VBox(fxRb1, fxRb2, fxRb3);
        	
        effectsBox.getChildren().addAll(effectsLabel, effectsGroupContain);
        
        
        HBox saveBox = new HBox();
        saveBox.setPadding(new Insets(0,0,0,0));
        saveBox.setAlignment(Pos.CENTER);
        saveBox.setSpacing(40);
        
        Btn saveButton = new Btn(null, 1, "Save Changes");
        saveButton.setMaxWidth(Double.MAX_VALUE); 
        saveButton.setPadding(new Insets(5, 10, 5, 10));
        saveButton.setOnAction(e -> 
        {
          if (texGroup.getSelectedToggle().equals(texRb1)) {
            currentSettings.setTextureQual(Settings.HIGH);
          }
          else if (texGroup.getSelectedToggle().equals(texRb2)) {
            currentSettings.setTextureQual(Settings.MED);
          }
          else {
            currentSettings.setTextureQual(Settings.LOW);
          }
          if (fxGroup.getSelectedToggle().equals(fxRb1)) {
            currentSettings.setEffectsQual(Settings.HIGH);
          }
          else if (fxGroup.getSelectedToggle().equals(fxRb2)) {
            currentSettings.setEffectsQual(Settings.MED);
          }
          else {
            currentSettings.setEffectsQual(Settings.LOW);
          }
          int width = Integer.parseInt(resCB.getValue().split(" x ")[0]);
          int height = Integer.parseInt(resCB.getValue().split(" x ")[1]);
          currentSettings.setVideoResWidth(width);
          currentSettings.setVideoResHeight(height);
          Main.db.saveSettings(temp, currentSettings);
          saveButton.increaseScore();
          Main.window.setScene(Main.settings);
        }
        );
        
        Btn cancelButton = new Btn(null, 1, "Cancel");
        cancelButton.setMaxWidth(Double.MAX_VALUE); 
        cancelButton.setPadding(new Insets(5, 10, 5, 10));
        cancelButton.setOnAction(e -> 
        {
          Main.window.setScene(Main.settings);
          cancelButton.increaseScore();
        }
        );
        
        saveBox.getChildren().addAll(saveButton, cancelButton);
        
        VBox vbSettings = new VBox();
        vbSettings.setSpacing(40);
        vbSettings.setPadding(new Insets(30, 30, 30, 30));
        vbSettings.setMinWidth(450);
        vbSettings.setAlignment(Pos.CENTER);
        vbSettings.getChildren().addAll(header, resolutionBox, textureBox, effectsBox, saveBox);
        
        settingsBox.getChildren().addAll(vbSettings);

        BorderPane root = new BorderPane();
        root.setRight(sidebar);
        root.setLeft(settingsBox);
        root.getStylesheets().add(getClass().getResource(t).toExternalForm());
        root.getStyleClass().add("root");

        homeGrid.getChildren().addAll(root);
       
        view = root;
    }
    
   public Node getView()
   {
       view.getStylesheets().add(getClass().getResource(t).toExternalForm());
       view.getStyleClass().add("root");
       return view;
   }
}
