package logic;

import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StoreView extends Application { 
  
  final int windowWidth = 600;
  boolean purchaseView = true;
  
  public static void main (String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle("Store View");
    VBox root = new VBox();
    root.getChildren().addAll(getHeader(), getBody());
    Scene scene = new Scene(root, windowWidth, 500);
    stage.setScene(scene);
    
    /*plus = new Button("+");
    plus.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        res = getAndCalc('+');
        System.out.println("plus = " + res);
        resText.setText("Result = " + res);
      }
    });*/
    
    //promts.getChildren().addAll(label1, aInput, bInput);
    //buttons.getChildren().addAll(plus, minus, mult, divide, resText);
    
    stage.show();
  }
  
  private HBox getHeader() {
    Label title = new Label("Store");
    title.setFont(Font.font(40));
    
    HBox header = new HBox();
    header.getChildren().add(title);
    header.setAlignment(Pos.CENTER);
    return header;
    
  }
  
  private HBox getBody() {
    HBox body = new HBox();
    
    body.setAlignment(Pos.CENTER);
    //body.setPrefWidth(windowWidth);
    body.setPadding(new Insets(10,0,0,0));
    
    body.getChildren().add(getSwitcher());
    if (purchaseView) {
      body.getChildren().add(getPurchase());
    } else {
      body.getChildren().add(getCustomize());
    }
    return body;
  }
  
  private HBox getSwitcher() {
    Button purchase  = new Button("Purchase");
    Button customize = new Button("Customize");
    purchase.setMinWidth(200);
    customize.setMinWidth(200);
    
    customize.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        purchaseView = false;
      }
    });
    
    purchase.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        purchaseView = true;
      }
    });
    
    HBox switcher = new HBox();
    switcher.getChildren().addAll(purchase, customize);
    switcher.setAlignment(Pos.CENTER);
    
    return switcher;
  }
  
  private HBox getPurchase() {
    List<Item> items = Main.theShop.getAllItems();
    HBox purchaseView = new HBox();
    
    for (Iterator<Item> i = items.iterator(); i.hasNext();) {
      purchaseView.getChildren().add(genPurchaseFrame(i.next()));
    }
    return purchaseView;
    
  }
  
  private HBox getCustomize() {
    List<Item> items = Main.theShop.getOwnedItems(Main.user);
    HBox customizedView = new HBox();
    
    for (Iterator<Item> i = items.iterator(); i.hasNext();) {
      customizedView.getChildren().add(genCustomizeFrame(i.next()));
    }
    return customizedView;
    
  }
  
  private VBox genPurchaseFrame(Item item) {
    VBox frame = new VBox();
    frame.setMaxWidth(250);
    frame.setMinWidth(250);
    frame.setMaxHeight(250);
    frame.setMinHeight(250);
    
    frame.setBorder(new Border(new BorderStroke(Color.BLACK, 
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    
    Label name = new Label(item.getName());
    HBox title = new HBox();
    title.getChildren().add(name);
    title.setAlignment(Pos.CENTER);
    
    frame.getChildren().add(title);
    
    HBox priceNBuy = new HBox();
    
    Label price = new Label(Integer.toString(item.getPrice()));
    Button buy = new Button("Buy");
    
    priceNBuy.getChildren().addAll(price, buy);
    frame.getChildren().add(priceNBuy);
    return frame;
    
  }
  
  private VBox genCustomizeFrame(Item item) {
    VBox frame = new VBox();
    frame.setMaxWidth(250);
    frame.setMinWidth(250);
    frame.setMaxHeight(250);
    frame.setMinHeight(250);
    
    frame.setBorder(new Border(new BorderStroke(Color.BLACK, 
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    
    Label name = new Label(item.getName());
    HBox title = new HBox();
    title.getChildren().add(name);
    title.setAlignment(Pos.CENTER);
    
    frame.getChildren().add(title);
    
    HBox equipBox = new HBox();
    
    Button equip = new Button("Use");
    
    equipBox.getChildren().add(equip);
    frame.getChildren().add(equipBox);
    return frame;
    
  }

}
