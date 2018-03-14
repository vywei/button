package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.Skin;
import logic.User;

public class TestUserSkinIntegration {
  
  @Test
  public void TestSkinPrice() {
    User user = new User("dfalessi123", 50, 2);
    Skin skin = user.getCurrentSkin();
    String sound = skin.getSound();
    assertEquals("click.mp3", sound);
  }

}
