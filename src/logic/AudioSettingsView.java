package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Slider;

public class AudioSettingsView 
{
	private static String themecss = "theme.css"; 
    private final BorderPane view;

    public AudioSettingsView()
    {
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));
        


        User tempU = Main.getUser();
    	Label headerLabel = new Label("Audio Settings");
        headerLabel.setMaxWidth(Double.MAX_VALUE);
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.getStylesheets().add(getClass().getResource(themecss).toExternalForm());
        headerLabel.getStyleClass().add("a-header"); 
        
        Sidebar sidebar = new Sidebar(tempU);
        tempU.register((Observer) sidebar);
       

        GridPane homeG = new GridPane();
        homeG.setPadding(new Insets(0, 0, 0, 0));
        homeG.setVgap(8);
        homeG.setHgap(10);
        homeG.getStylesheets().add(getClass().getResource(themecss).toExternalForm());
        homeG.getStyleClass().add("root");
        
        HBox settingsB = new HBox();
        settingsB.setPadding(new Insets(0,0,0,0));
        settingsB.setMinWidth(450);
        settingsB.setAlignment(Pos.CENTER);
        
        HBox resBox = new HBox();
        resBox.setPadding(new Insets(0,0,0,0));
        resBox.setMinWidth(350);
        resBox.setAlignment(Pos.CENTER);
        resBox.setSpacing(40);
        Slider volumeSlider = new Slider(0,1,.5);

        Label volumeLabel = new Label("Volume:");
        resBox.getChildren().addAll(volumeLabel,volumeSlider/*resLabel, resCB*/);
        
        HBox texBox = new HBox();
        texBox.setPadding(new Insets(0,0,0,0));
        texBox.setMinWidth(350);
        texBox.setAlignment(Pos.CENTER);
        texBox.setSpacing(40);
        
        Label texLabel = new Label("Texture Quality:");
        
        final ToggleGroup texGroup = new ToggleGroup();

        RadioButton texRb1 = new RadioButton("High");
        texRb1.setToggleGroup(texGroup);
        texRb1.setSelected(true);

        RadioButton texRb2 = new RadioButton("Medium");
        texRb2.setToggleGroup(texGroup);
         
        RadioButton texRb3 = new RadioButton("Low");
        texRb3.setToggleGroup(texGroup);
        
        VBox texGroupContain = new VBox(texRb1, texRb2, texRb3);
        	
        texBox.getChildren().addAll(texLabel, texGroupContain);
        
        HBox effectsBox = new HBox();
        effectsBox.setPadding(new Insets(0,0,0,0));
        effectsBox.setMinWidth(350);
        effectsBox.setAlignment(Pos.CENTER);
        effectsBox.setSpacing(40);
        
        Label effectsLabel = new Label("Effects Volume:");
        
        final ToggleGroup fxGroup = new ToggleGroup();

        RadioButton fxRb1 = new RadioButton("High");
        fxRb1.setToggleGroup(fxGroup);
        fxRb1.setSelected(true);

        RadioButton fxRb2 = new RadioButton("Medium");
        fxRb2.setToggleGroup(fxGroup);
         
        RadioButton fxRb3 = new RadioButton("Low");
        fxRb3.setToggleGroup(fxGroup);
        
        VBox effectsGroupContain = new VBox(fxRb1, fxRb2, fxRb3);
        	
        effectsBox.getChildren().addAll(effectsLabel, effectsGroupContain);
        
        
        HBox saveB = new HBox();
        saveB.setPadding(new Insets(0,0,0,0));
        saveB.setAlignment(Pos.CENTER);
        saveB.setSpacing(40);
        
        Button saveButton1 = new Button("Save Changes");
        saveButton1.setMaxWidth(Double.MAX_VALUE); 
        saveButton1.setPadding(new Insets(5, 10, 5, 10));
        saveButton1.setOnAction(e -> Main.window.setScene(Main.settings));
        
        Button cancelButton1 = new Button("Cancel");
        cancelButton1.setMaxWidth(Double.MAX_VALUE); 
        cancelButton1.setPadding(new Insets(5, 10, 5, 10));
        cancelButton1.setOnAction(e -> Main.window.setScene(Main.settings));
        
        saveB.getChildren().addAll(saveButton1, cancelButton1);
        
        VBox vbSettings1 = new VBox();
        vbSettings1.setSpacing(40);
        vbSettings1.setPadding(new Insets(30, 30, 30, 30));
        vbSettings1.setMinWidth(450);
        vbSettings1.setAlignment(Pos.CENTER);
        vbSettings1.getChildren().addAll(headerLabel, resBox, texBox, effectsBox, saveB);
        
        settingsB.getChildren().addAll(vbSettings1);

        BorderPane rootV = new BorderPane();
        rootV.setRight(sidebar);
        rootV.setLeft(settingsB);
        rootV.getStylesheets().add(getClass().getResource(themecss).toExternalForm());
        rootV.getStyleClass().add("root");

        homeG.getChildren().addAll(rootV);
       
        view = rootV;
    }
    public Node getView()
    {
        view.getStylesheets().add(getClass().getResource(themecss).toExternalForm());
        view.getStyleClass().add("rootV");
        return view;
    }

}