package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.Skin;
import logic.User;

public class TestUser {

  @Test
  public void TestSkinPrice() {
    User user = new User("dfalessi123", 50, 2);
    Skin skin = user.getCurrentSkin();
    assertEquals(0, skin.getPrice());
  }
  
  @Test
  public void TestPressedSkinName() {
    User user = new User("dfalessi123", 50, 2);
    Skin skin = user.getCurrentSkin();
    assertEquals("red_button_pressed.png", skin.getImagePressed());
  }
  
  

}
