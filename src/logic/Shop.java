package logic;

import java.util.*;
import armdb.QueryResult;
import armdb.SQLQueryException;

public class Shop {
  DatabaseMock dbm = new DatabaseMock();
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
  
  public List<Item> getOwnedItems(Player p) {
    // Create result list
    List<Item> playerItems = new ArrayList<Item>();
    
    // Create query and result
    SQLSelect query = new SQLSelect(db.getCH());
    QueryResult qr;  
    
    // Execute the query
    try {
      qr = query.result("items_owned JOIN item ON (items_owned.item_id = item.id)", 
          new ArrayList<String>(), "WHERE player_id = '" + p.getId() +"'"); 

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
      Item newItem = new Item(id, name, price);
      list.add(newItem);
    }
  }
  
  public List<Item> getShopItems(Player p) {
    List<Item> allItems = getAllItems();
    List<Item> ownedItems = getOwnedItems(p);
    allItems.removeAll(ownedItems);
    return allItems;
  }
  
  public boolean purchaseItem(Item i, Player p) {
    if(p.getBalance() >= i.getPrice()) {
        p.subtractFromBalance(i.getPrice());
        
        SQLInsert query = new SQLInsert(db.getCH());
        
        ArrayList<String> cols = new ArrayList<String>();
        cols.add("player_id");
        cols.add("item_id");
        
        ArrayList<String> vals = new ArrayList<String>();
        cols.add(p.getId().toString());
        cols.add(i.getId().toString());
        
        query.result("items_owned", cols, vals);
    } 
    else {
        return false;
    }
  
  }
  
  public List<Item> getShopItemsMock(Player p) {
	  List<Item> allItems = dbm.getAllItems();
	  List<Item> ownedItems = dbm.getPlayerOwnedItems(p.getUsername());
	  allItems.removeAll(ownedItems);
	  return allItems;
  }
  
  public List<Item> getOwnedItemsMock(Player p) {
	  return dbm.getPlayerOwnedItems(p.getUsername());
  }
  
  public List<Item> getAllItemsMock() {
	  return dbm.getAllItems();
  }
  
  
  public boolean purchaseItemMock(Item i, Player p) {
	  if(p.getBalance() >= i.getPrice()) {
		  p.subtractFromBalance(i.getPrice());
		  return dbm.insertOwnedItem(p.getUsername(), i);
	  } else {
		  return false;
	  }
    
  }
  
  //might not be part of the shop? Idk
  public boolean equipItemMock(Item i, Player p) {
	  return p.setActiveItme(i);
  }
  
}
