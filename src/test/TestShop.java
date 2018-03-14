package test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import logic.Shop;
import logic.Database;
import logic.Item;
import logic.User;

public class TestShop {
  
  @Test
  public void TestGetAllItems() {
    Shop a = new Shop();
    boolean bool1 = a.getAllItems() instanceof List;
    boolean bool2 = a.getAllItems().size() > 0;
    boolean bool3 = a.getAllItems().get(0) instanceof Item;
    assertTrue(bool1 && bool2 && bool3);
  }
  
  @Test
  public void TestGetOwnedItems() {
    Database db = Database.getDatabase();
    User u = db.loginUser("testuser", "password");
    Shop b = new Shop();
    boolean bool1 = b.getOwnedItems(u) instanceof List;
    boolean bool2 = b.getOwnedItems(u).size() > 0;
    boolean bool3 = b.getOwnedItems(u).get(0) instanceof Item;
    assertTrue(bool1 && bool2 && bool3);
  }

}
