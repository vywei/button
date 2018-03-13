package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import logic.Database;
import logic.User;


public class TestDatabaseUserIntegration {
	 @Test
	  public void TestUserFromLogin() {
	    Database db = Database.getDatabase();
	    User u = db.loginUser("testuser", "password");
	    assertEquals("testuser", u.getUsername());
	  }
}
