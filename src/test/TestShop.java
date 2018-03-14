package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import armdb.SQLQueryException;
import logic.Shop;
import logic.Database;
import logic.Item;
import logic.SQLSelect;
import logic.User;

public class TestShop {
  
  @Test
  public void TestGetAllItems() {
    Database db = Database.getDatabase();
    SQLSelect query = new SQLSelect(db.getCH());
    try {
      query.result("item", new ArrayList<String>(), ""); 
    }
    catch(SQLQueryException e){  
      //if database connection is down, exit test
       assertTrue(true);
       return;
    }
    
      Shop a = new Shop();
      boolean bool1 = a.getAllItems() instanceof List;
      boolean bool2 = a.getAllItems().size() > 0;
      boolean bool3 = a.getAllItems().get(0) instanceof Item;
      assertTrue(bool1 && bool2 && bool3);
    
  }
  
  @Test
  public void TestGetOwnedItems() {
    Database db = Database.getDatabase();
    SQLSelect query = new SQLSelect(db.getCH());
    try {
      query.result("item", new ArrayList<String>(), ""); 
    }
    catch(SQLQueryException e){  
      //if database connection is down, exit test
       assertTrue(true);
       return;
    }
      User u = db.loginUser("testuser", "password");
      Shop b = new Shop();
      boolean bool1 = b.getOwnedItems(u) instanceof List;
      boolean bool2 = b.getOwnedItems(u).size() > 0;
      boolean bool3 = b.getOwnedItems(u).get(0) instanceof Item;
      assertTrue(bool1 && bool2 && bool3);
    
  }

}
