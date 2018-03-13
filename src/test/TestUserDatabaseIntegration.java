package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import logic.Database;
import logic.User;


public class TestUserDatabaseIntegration {
	 @Test
	  public void TestValidateAndHashPassword() {
	    Database db = Database.getTestDatabase();
	    User u = new User();
	    String result = db.encryptPassword(u.validatePassword("testpassword"));
	    assertEquals("e16b2ab8d12314bf4efbd6203906ea6c", result.toLowerCase());
	  }
}
