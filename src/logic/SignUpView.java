package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SignUpView {
  private final BorderPane view;

  public SignUpView()
  {
      VBox signUpBox = new VBox();
      double boxWidth = 200;
      double height = Main.SCREEN_HEIGHT;
      double width = Main.SCREEN_WIDTH;
      
      Image image = new Image(Main.class.getResourceAsStream("images/signup.png"));
      ImageView iv = new ImageView();
      iv.setImage(image);
      iv.setFitWidth(450);
      iv.setPreserveRatio(true);
      iv.setSmooth(true);
      iv.setCache(true);

      HBox imageBox = new HBox();
      imageBox.setPadding(new Insets(-225,0,0,-130));
      imageBox.getChildren().add(iv);
      
      GridPane newUse = new GridPane();
      newUse.setPadding(new Insets(0, 0, 0, 0));
      newUse.setVgap(8);
      newUse.setHgap(10);
      
      // Sign Up Input
      TextField fNameInput = new TextField();
      fNameInput.setPromptText("First Name");
      fNameInput.setMaxWidth(Double.MAX_VALUE);
      
      TextField lNameInput = new TextField("");
      lNameInput.setPromptText("Last Name");
      lNameInput.setMaxWidth(Double.MAX_VALUE);

      TextField userSignUpInput = new TextField("");
      userSignUpInput.setPromptText("Username");
      userSignUpInput.setMaxWidth(Double.MAX_VALUE);

      TextField passSignUpInput = new PasswordField();
      passSignUpInput.setPromptText("Password");
      passSignUpInput.setMaxWidth(Double.MAX_VALUE);

      TextField confirmPassInput = new PasswordField();
      confirmPassInput.setPromptText("Confirm Password");
      confirmPassInput.setMaxWidth(Double.MAX_VALUE);

      Button createSignUpButton = new Button("Create Account");
      createSignUpButton.setMaxWidth(Double.MAX_VALUE);
      createSignUpButton.setOnAction(e -> 
      {
    	  if (!passSignUpInput.getText().equals("") && !userSignUpInput.getText().equals("")) {
	    	  if (passSignUpInput.getText().equals((confirmPassInput.getText()))) {
	    		  Database db = Database.getDatabase();
	    		  if (db.createAccount(userSignUpInput.getText(), passSignUpInput.getText()) != null) {
	    			  Main.window.setScene(Main.login);
	    		  }
	    		  else {
	    			  final Stage dialog = new Stage();
	                  dialog.initModality(Modality.APPLICATION_MODAL);
	                  dialog.initOwner(Main.window);
	                  VBox dialogVbox = new VBox(20);
	                  dialogVbox.setAlignment(Pos.CENTER);
	                  Label msgLabel = new Label("Username not available.");
	                  msgLabel.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD,14));
	                  dialogVbox.getChildren().add(msgLabel);
	                  Scene dialogScene = new Scene(dialogVbox, 250, 100);
	                  String sheet = Main.getSheet();
	            	  dialogScene.getStylesheets().add(sheet);
	                  dialog.setScene(dialogScene);
	                  dialog.show();
	    		  }
	    	  }
	    	  else {
	    		  final Stage dialog = new Stage();
                  dialog.initModality(Modality.APPLICATION_MODAL);
                  dialog.initOwner(Main.window);
                  VBox dialogVbox = new VBox(20);
                  dialogVbox.setAlignment(Pos.CENTER);
                  Label msgLabel = new Label("Passwords do not match.");
                  msgLabel.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD,14));
                  dialogVbox.getChildren().add(msgLabel);
                  Scene dialogScene = new Scene(dialogVbox, 250, 100);
                  String sheet = Main.getSheet();
            	  dialogScene.getStylesheets().add(sheet);
                  dialog.setScene(dialogScene);
                  dialog.show();
	    	  }
    	  }
      }
      );
      
      Button cancelButton = new Button(Main.cancelString);
      cancelButton.setMaxWidth(Double.MAX_VALUE);
      cancelButton.setOnAction(e -> Main.window.setScene(Main.login));

      signUpBox.setSpacing(10);
      
      // Sets the width of the signup box.
      signUpBox.setMaxWidth(boxWidth);
      
      // Adds text fields and buttons to the vbox
      signUpBox.getChildren().addAll(fNameInput, lNameInput, userSignUpInput, passSignUpInput);
      signUpBox.getChildren().addAll(confirmPassInput, createSignUpButton, cancelButton);
      newUse.getChildren().addAll(imageBox, signUpBox);
      
      // Puts the box in the middle-ish part of the screen
      newUse.setPadding(new Insets(height/2-100, width/2, height/2, width/2-90));
      
      /*final BooleanProperty firstTime = new SimpleBooleanProperty(true); 
      fNameInput.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
          if(newValue && firstTime.get()){
              signUpBox.requestFocus(); 
              firstTime.setValue(false); 
          }
      });*/
      
      view = new BorderPane();
      view.setCenter(newUse);
      
  }
  public Node getView()
  {
      view.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
      return view;   
  }
}
