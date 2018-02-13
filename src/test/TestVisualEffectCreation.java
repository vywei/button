package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.VisualEffect;

public class TestVisualEffectCreation {
  @Test
  public void TestNewEffect() {
    VisualEffect TestEffect = new VisualEffect(13, "testEffect", 130);
    int output = TestEffect.getPrice();
    assertEquals(130, output);
  }
}
