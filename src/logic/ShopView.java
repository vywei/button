package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShopView 
{
    private static String t = "theme.css"; 
    private final BorderPane view;
    protected static Scene store;
    boolean purchaseView = true;
    private final int spHeight = 300;
    private VBox body;
     
    public ShopView()
    {    
        User temp = Main.getUser();
      
        Sidebar sidebar = new Sidebar(temp);
        temp.register((Observer) sidebar);
        
        VBox root = new VBox();
        root.setMaxWidth(Double.MAX_VALUE);
        root.setMinWidth(450);
        root.getChildren().addAll(getHeader(), getBody());
        
        BorderPane pane = new BorderPane();
        pane.setRight(sidebar);
        pane.setLeft(root);
        pane.getStylesheets().add(getClass().getResource(t).toExternalForm());
        pane.getStyleClass().add("root");
        
        view = pane;
    }
    
   public Node getView()
   {
       view.getStylesheets().add(getClass().getResource(t).toExternalForm());
       view.getStyleClass().add("root");
       return view;
   }
   
   private HBox getHeader() 
   {
     Label title = new Label("Store");
     title.getStylesheets().add(getClass().getResource(t).toExternalForm());
     title.getStyleClass().add("a-header"); 
     
     HBox header = new HBox();
     header.getChildren().add(title);
     header.setAlignment(Pos.CENTER);
     return header;
     
   }
   
   private VBox getBody() 
   {
     body = new VBox();
     
     body.setAlignment(Pos.CENTER);
     body.setPrefHeight(Main.SCREEN_HEIGHT);
     body.setPrefWidth(450);
     body.setPadding(new Insets(10,0,0,0));
     
     body.getChildren().add(getSwitcher(body));
     if (purchaseView) 
     {
       ScrollPane sp = new ScrollPane();
       sp.setFitToWidth(true);
       sp.setContent(getPurchase());
       sp.setPrefHeight(spHeight);
       sp.getStylesheets().add(getClass().getResource(t).toExternalForm());
       sp.getStyleClass().add("root");
       
       body.getChildren().add(sp);
     } 
     else 
     {
       ScrollPane sp = new ScrollPane();
       sp.setFitToWidth(true);
       sp.setContent(getCustomize());
       sp.setPrefHeight(spHeight);
       sp.getStylesheets().add(getClass().getResource(t).toExternalForm());
       sp.getStyleClass().add("root");
       
       body.getChildren().add(sp);
     }
     return body;
   }
   
   private HBox getSwitcher(VBox body) 
   {
     Button purchase  = new Button("Purchase");
     Button customize = new Button("Customize");
     purchase.setMinWidth(200);
     customize.setMinWidth(200);
     
     customize.setOnAction(new EventHandler<ActionEvent>() 
     {
       public void handle(ActionEvent event) 
       {
         purchaseView = false;
         int size = body.getChildren().size();
         body.getChildren().remove(size - 1);
         
         ScrollPane sp = new ScrollPane();
         sp.setFitToWidth(true);
         sp.setContent(getCustomize());
         sp.setPrefHeight(spHeight);
         sp.getStylesheets().add(getClass().getResource(t).toExternalForm());
         sp.getStyleClass().add("root");
         
         body.getChildren().add(sp);
       }
     }
     );
     
     purchase.setOnAction(new EventHandler<ActionEvent>() 
     {
       public void handle(ActionEvent event) 
       {
         purchaseView = true;
         int size = body.getChildren().size();
         body.getChildren().remove(size - 1);
         ScrollPane sp = new ScrollPane();
         sp.setFitToWidth(true);
         sp.setContent(getPurchase());
         sp.setPrefHeight(spHeight);
         sp.getStylesheets().add(getClass().getResource(t).toExternalForm());
         sp.getStyleClass().add("root");
         
         body.getChildren().add(sp);
       }
     }
     );
     
     HBox switcher = new HBox();
     switcher.getChildren().addAll(purchase, customize);
     switcher.setAlignment(Pos.CENTER);
     switcher.setPadding(new Insets(0,0,20,0));
     
     return switcher;
   }
   
   private TilePane getPurchase() 
   {
     //List<Item> items = dummyList(); 
	 List<Item> items = Main.getShop().getShopItems(Main.getUser());
     
     TilePane purchaseView = new TilePane();
     purchaseView.setHgap(10);
     purchaseView.setVgap(10);
     
     for (Iterator<Item> i = items.iterator(); i.hasNext();) 
     {
       purchaseView.getChildren().add(genPurchaseFrame(i.next()));
     }
     
     purchaseView.setTileAlignment(Pos.CENTER);
     purchaseView.setAlignment(Pos.CENTER);
     purchaseView.getStylesheets().add(getClass().getResource(t).toExternalForm());
     purchaseView.getStyleClass().add("root");
     purchaseView.setPrefHeight(spHeight);
     return purchaseView;
     
   }
   
   private TilePane getCustomize() 
   {
     //List<Item> items = dummyOwnedList(); 
	 List<Item> items = Main.getShop().getOwnedItems(Main.user);
     TilePane customizedView = new TilePane();
     customizedView.setHgap(10);
     customizedView.setVgap(10);
     
     for (Iterator<Item> i = items.iterator(); i.hasNext();) 
     {
       customizedView.getChildren().add(genCustomizeFrame(i.next()));
     }
     
     customizedView.setTileAlignment(Pos.CENTER);
     customizedView.setAlignment(Pos.CENTER);
     customizedView.getStylesheets().add(getClass().getResource(t).toExternalForm());
     customizedView.getStyleClass().add("root");
     customizedView.setPrefHeight(spHeight);
     return customizedView;
     
   }
   
   private void refreshItems()
   {
	   ScrollPane sp = new ScrollPane();
       sp.setFitToWidth(true);
       sp.setContent(getPurchase());
       sp.setPrefHeight(spHeight);
       sp.getStylesheets().add(getClass().getResource(t).toExternalForm());
       sp.getStyleClass().add("root");
       
       int idx = body.getChildren().size() - 1;
       body.getChildren().remove(idx);
       body.getChildren().add(sp);
   }
   
   private VBox genPurchaseFrame(Item item) 
   {
     VBox frame = new VBox();
     frame.setMaxWidth(200);
     frame.setMinWidth(200);
     frame.setMaxHeight(225);
     frame.setMinHeight(225);
     
     frame.setBorder(new Border(new BorderStroke(Color.BLACK, 
         BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
     
     Label name = new Label(item.getName());
     name.setFont(Font.font(20));
     name.setTextFill(Color.WHITE);
     name.setPadding(new Insets(5,0,0,0));
     HBox title = new HBox();
     title.getChildren().add(name);
     title.setAlignment(Pos.CENTER);
     
     
     frame.getChildren().add(title);
     
     BorderPane priceNBuy = new BorderPane();
     
     Label price = new Label(Integer.toString(item.getPrice()) + " pts");
     price.setFont(Font.font(12));
     price.setTextFill(Color.WHITE);
     Button buy = new Button("Buy");
    
     buy.setOnAction(new EventHandler<ActionEvent>() 
     {
       public void handle(ActionEvent event) 
       {
         boolean result = Main.getShop().purchaseItem(item, Main.getUser());
         if (result == true) 
         {
        	 refreshItems();
         }
         else
         {
        	 final Stage dialog = new Stage();
             dialog.initModality(Modality.APPLICATION_MODAL);
             dialog.initOwner(Main.window);
             VBox dialogVbox = new VBox(20);
             dialogVbox.getChildren().add(new Text("Not enough points to purchase this item!"));
             Scene dialogScene = new Scene(dialogVbox, 300, 200);
             dialog.setScene(dialogScene);
             dialog.show();
         }
       }
      });
     
     Image image = new Image(Main.class.getResourceAsStream(item.getImage()));

     VBox content = new VBox();
     content.setAlignment(Pos.CENTER);
     
     ImageView iv = new ImageView();
     iv.setImage(image);
     iv.setFitWidth(120);
     iv.setPreserveRatio(true);
     iv.setSmooth(true);
     iv.setCache(true);
     
     priceNBuy.setLeft(price);
     priceNBuy.setRight(buy);
     priceNBuy.setPadding(new Insets(5,5,0,5));
     
     content.getChildren().addAll(priceNBuy, iv);
     
     frame.getChildren().addAll(content);
     return frame;
     
   }
   
   private VBox genCustomizeFrame(Item item) 
   {
     VBox frame = new VBox();
     frame.setMaxWidth(200);
     frame.setMinWidth(200);
     frame.setMaxHeight(225);
     frame.setMinHeight(225);
     
     frame.setBorder(new Border(new BorderStroke(Color.BLACK, 
         BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
     
     Label name = new Label(item.getName());
     name.setFont(Font.font(20));
     name.setTextFill(Color.WHITE);
     name.setPadding(new Insets(5,0,0,0));
     HBox title = new HBox();
     title.getChildren().add(name);
     title.setAlignment(Pos.CENTER);
     
     frame.getChildren().add(title);
     
     BorderPane equipBox = new BorderPane();
     
     Button equip = new Button("Use");
     equip.setOnAction(new EventHandler<ActionEvent>() 
     {
       public void handle(ActionEvent event) 
       {
    	  if (item.getType() == Item.SKIN)
    	  {
    		  Main.getUser().changeSkin((Skin)item);
    	  }
       }
      });
     
     Image image = new Image(Main.class.getResourceAsStream(item.getImage()));

     VBox content = new VBox();
     content.setAlignment(Pos.CENTER);
     
     ImageView iv = new ImageView();
     iv.setImage(image);
     iv.setFitWidth(120);
     iv.setPreserveRatio(true);
     iv.setSmooth(true);
     iv.setCache(true);
     
     equipBox.setRight(equip);
     equipBox.setPadding(new Insets(5,5,0,5));
     
     content.getChildren().addAll(equipBox, iv);
     
     frame.getChildren().add(content);
     return frame;
     
   }
}
