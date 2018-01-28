import java.util.*;

public class Shop {
  DatabaseMock db = new DatabaseMock();
  
  public List<Item> getShopItems(Player p) {
    //TODO: get list of items available for purchase from Database
	  List<Item> allItems = db.getAllItems();
	  List<Item> ownedItems = db.getPlayerOwnedItems(p.getUsername());
	  allItems.removeAll(ownedItems);
	  return allItems;
  }
  
  public List<Item> getOwnedItems(Player p) {
    //TODO: get list of items owned by p from Database
	  return db.getPlayerOwnedItems(p.getUsername());
  }
  
  public List<Item> getAllItems() {
    //TODO: get all possible shop items from Database
	  return db.getAllItems();
  }
  
  public boolean purchaseItem(Item i, Player p) {
	  if(p.getBalance() >= i.getPrice()) {
		  p.subtractFromBalance(i.getPrice());
		  return db.insertOwnedItem(p.getUsername(), i);
	  } else {
		  return false;
	  }
    
  }
  
  //might not be part of the shop? Idk
  public boolean equipItem(Item i, Player p) {
	  return p.setActiveItme(i);
  }
  
}
