package logic;

import java.util.*;
import armdb.QueryResult;
import armdb.SQLQueryException;

public class Shop {
  Database db = new Database();
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
    
    // Loop through all rows in the result
    while(qr.nextFlag()) {
      
      // Parse the column data
      int id = Integer.parseInt(qr.getValue("id"));
      String name = (qr.getValue("name"));
      int price = Integer.parseInt(qr.getValue("price"));
      
      //Instantiate new item and insert into result list
      Item newItem = new Skin(id, name, price);
      list.add(newItem);
    }
  }
  
  public List<Item> getShopItems(User u) {
    List<Item> allItems = getAllItems();
    List<Item> ownedItems = getOwnedItems(u);
    allItems.removeAll(ownedItems);
    return allItems;
  }
  
  public boolean purchaseItem(Item i, User u) throws SQLQueryException {
    if(u.getBalance() >= i.getPrice()) {
        u.subtractFromBalance(i.getPrice());
        
        SQLInsert query = new SQLInsert(db.getCH());
        
        ArrayList<String> cols = new ArrayList<String>();
        cols.add("player_id");
        cols.add("item_id");
        
        ArrayList<String> vals = new ArrayList<String>();
        cols.add(Integer.toString(u.getID()));
        cols.add(Integer.toString(i.getID()));
        
        query.result("items_owned", cols, vals);
        return true;
    } 
    else {
        return false;
    }
  
  }
  
}
