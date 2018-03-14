package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.Item;
import logic.Skin;

public class TestItemFunc {

  @Test public void TestItemName()
  {
    Item item = new Skin(12345, "Waterfall", 500, "image.png", "imagePressed.png", "sound.mp3");
    String expected = "Waterfall";
    String actual = item.getName();
    assertEquals(expected, actual);
  }
  
  @Test public void TestItemPrice()
  {
    Item item = new Skin(12345, "Waterfall", 500, "image.png", "imagePressed.png", "sound.mp3");
    int expected = 500;
    int actual = item.getPrice();
    assertEquals(expected, actual);
  }
}
