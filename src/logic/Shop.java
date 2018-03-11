package logic;

import java.util.*;
import armdb.QueryResult;
import armdb.SQLQuery;
import armdb.SQLQueryException;
import armdb.SQLUpdateException;

public class Shop {
  private static Database db;
  
  public Shop() {
    db = Database.getDatabase();
  }
  
  public List<Item> getAllItems() {
    // Create result list
    List<Item> allItems = new ArrayList<Item>();
    
    // Create query and result
    SQLSelect query = new SQLSelect(db.getCH());
    QueryResult qr;  
    
    // Execute the query
    try {
      qr = query.result("item", new ArrayList<String>(), ""); 

      // Convert each row into an item
      parseItems(qr, allItems);
    }
    catch(SQLQueryException e){                   
        System.out.println(e.getMessage());           
    }
    
    return allItems;
  }
  
  public List<Item> getOwnedItems(User u) {
    // Create result list
    List<Item> playerItems = new ArrayList<Item>();
    
    // Create query and result
    SQLSelect query = new SQLSelect(db.getCH());
    QueryResult qr;  
    
    // Execute the query
    try {
      qr = query.result("items_owned JOIN item ON (items_owned.item_id = item.id)", 
          new ArrayList<String>(), "WHERE player_id = '" + u.getID() +"'"); 

      // Convert each row into an item
      parseItems(qr, playerItems);
    }
    catch(SQLQueryException e){                   
        System.out.println(e.getMessage());           
    }
    
    return playerItems;
  }
  
  private void parseItems(QueryResult qr, List<Item> list) {
    while(qr.nextFlag()) {
      
      int id = Integer.parseInt(qr.getValue("id"));
      String name = (qr.getValue("name"));
      int price = Integer.parseInt(qr.getValue("price_points"));
      String image = qr.getValue("image");
      String imagePressed = qr.getValue("image_pressed");
      int type = Integer.parseInt(qr.getValue("type"));
      
      //Instantiate new item and insert into result list
      Item newItem;
      if (type == Item.SKIN) {
    	  newItem = new Skin(id, name, price, image, imagePressed);
          list.add(newItem);
      }
    }
  }
  
  public List<Item> getShopItems(User u) {
    List<Item> allItems = getAllItems();
    List<Item> ownedItems = getOwnedItems(u);
    allItems.removeAll(ownedItems);
    return allItems;
  }
  
  public boolean purchaseItem(Item i, User u) {
    if(u.getBalance() >= i.getPrice()) {
        u.subtractFromBalance(i.getPrice());
        
        SQLInsert query = new SQLInsert(db.getCH());
        
        ArrayList<String> cols = new ArrayList<String>();
        cols.add("player_id");
        cols.add("item_id");
        
        ArrayList<String> vals = new ArrayList<String>();
        vals.add(Integer.toString(u.getID()));
        vals.add(Integer.toString(i.getID()));
        
        try {
        	query.result("items_owned", cols, vals);
        }
        catch (SQLUpdateException ex) {
        	System.out.println("Error purchasing item.");
        }
        
        u.setItems(getOwnedItems(u));
        
        return true;
    } 
    else {
        return false;
    }
  
  }
  
}
