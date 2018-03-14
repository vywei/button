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
}

