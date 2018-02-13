package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.User;

public class TestUserCreation {
  @Test
  public void TestNewUser() {
    User TestUser = new User("testuser", "testpass");
    String output = TestUser.getUsername();
    assertEquals("testuser", output);
  }
}
