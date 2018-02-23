package test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import logic.Observer;
import logic.User;
import logic.Item;

public class TestUser_unit {
  @Test
  public void testValidateUsername() {
      User user = new User();
      user.setUsername("Doot123456789");
      assertTrue(user.getUsername().equals("Doot123456789"));
  }
  
  @Test
  public void testShortValidateUsername() {
      User user = new User();
      user.setUsername("2smol");
      assertTrue(user.getUsername() == null);
  }
  
  @Test
  public void testValidatePassword() {
      User user = new User();
      user.setPassword("Doot123456789");
      assertTrue(user.getPassword().equals("Doot123456789"));
  }
  
  @Test
  public void testShortValidatePassword() {
      User user = new User();
      user.setPassword("2smol");
      assertTrue(user.getPassword() == null);
  }
  
  @Test
  public void testUserConstructor() {
      User user = new User("Doot123456789", "Doot123456789");
      assertTrue(user.getPassword().equals("Doot123456789") && user.getUsername().equals("Doot123456789"));
  }
  
  @Test
  public void testRegisterValid() {
      User user = new User("12345678", "1234567890");
      User watch = new User("Stalker", "1234567890");
      user.register(watch);
      ArrayList<Observer> test = user.getObservers();
      assertEquals(1, test.size(), 0);
  }
  
  @Test
  public void TestUser(){
      String skinName = "tannaSkin";
      User u = new User("username0","password0");
      u.addNewSkin(skinName);
      assertEquals(u.getCurrentSkin(),skinName);
  }
  
}
