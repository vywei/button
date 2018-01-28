import java.util.*;

public class Shop {
  DatabaseMock db = new DatabaseMock();
  
  public List<Item> getShopItems(Player p) {
    //TODO: get list of items available for purchase from Database
  }
  
  public List<Item> getOwnedItems(Player p) {
    //TODO: get list of items owned by p from Database
  }
  
  public List<Item> getAllItems() {
    //TODO: get all possible shop items from Database
  }
  
  public boolean purchaseItem(Item i, Player p) {
    return db.insertOwnedItem(p.getUsername(), i);
  }
  
}
