package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.*;

public class TestSkin {

  @Test
  public void TestGetPrice() {
    Skin item = new Skin(0, "testSkin", 42, "", "", "");
    assertEquals(42, item.getPrice());
  }

}
