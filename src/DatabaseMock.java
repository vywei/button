import java.util.*;

//TODO replace these with real classes
class Player {}
class Item {}

public class DatabaseMock {
  private HashMap<Integer, Item> allItems = new HashMap();
  private HashMap<Integer, Player> allPlayers = new HashMap();
  
  //placeholder for tracking items just on local player until database is setup
  private List<Item> ownedItems = new List<Item>();
  
  //returns the id of the newly registered players, or -1 if there was an error
  public int registerNewPlayer(String username) {
    //TODO:: Construct a real player
    Player toBeAdded = new Player();
    int userHash = username.hashCode();
    allPlayers.put(userHash, toBeAdded);
    return userHash;
  }
  
  public List<Item> getAllItems() {
    return new ArrayList<Item>(allItems.values());
  }
  
  public Item getItem(int id) {
    return allItems.get(id);
  }
  
  public boolean insertOwnedItem(String username, Item i) {
    if (ownedItems.contains(i)) {
      return false;
    }
    ownedItems.add(i);
    return true;
  }
  
  public List<Item> getPlayerOwnedItems(String username) {
    return ownedItems;
  }
  
  public boolean updatePlayerBalance(String username) {
	  
  }
  
  public int getPlayerBalance(String username) {
	  
  }
}
