package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.Item;
import logic.Settings;
import logic.Skin;
import logic.User;

public class TestSkinFunc {
    
    @Test public void TestSkinID()
    {       
      Skin skin = new Skin(12345, "Waterfall", 500, "", "", "");
      int expected = 12345;
      int actual = skin.getID();
      assertEquals(expected, actual, 0);
    }
    
    @Test public void TestSkinName()
    {       
      Skin skin = new Skin(12345, "", 500, "", "imagePressed.png", "");
      String expected = "imagePressed.png";
      String actual = skin.getImagePressed();
      assertEquals(expected, actual);
    }
    
    @Test public void TestItemIntegration()
    {
      Item item = new Skin(12345, "Waterfall", 500, "image.png", "imagePressed.png", "sound.mp3");
      String expected = "Waterfall";
      String actual = item.getName();
      assertEquals(expected, actual);
    }
    
    @Test public void TestIntegration()
    {
      User user = new User ("testuser", "test");
      Settings settings = new Settings(user);
      settings.setMusicVol(50);
      settings.saveSettings(user);
      int expected = 50;
      int actual = settings.getMusicVol();
      assertEquals(expected, actual, 0);
    }
}

