package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.Item;

public class TestItem {

  @Test
  public void TestGetPrice() {
    Item item = new Item(0, "testItem", 42);
    assertEquals(42, item.getPrice());
  }

}
