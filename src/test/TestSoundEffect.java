package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.*;

public class TestSoundEffect {

  @Test
  public void TestgetID() {
    Item soundEffect = new SoundEffect(3, "merica", 32);
    assertEquals(3, soundEffect.getID());
  }

}
