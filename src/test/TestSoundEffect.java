package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.SoundEffect;

public class TestSoundEffect {
  
  @Test
  public void TestGetPrice() {
    SoundEffect e = new SoundEffect(123, "testEffect", 467);
    assertEquals(e.getPrice(), 467);
  }
  
  @Test
  public void TestGetID() {
    SoundEffect e = new SoundEffect(123, "testEffect", 467);
    assertEquals(e.getID(), 123);
  }
}
