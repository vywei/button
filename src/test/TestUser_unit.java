package test;
import static org.junit.Assert.*;
import org.junit.Test;
import logic.Item;
import logic.User;
public class TestUser_unit {
	@Test
	public void TestUser(){
		String skinName = "tannaSkin";
		User u = new User("username0","password0");
		u.addNewSkin(skinName);
		assertEquals(u.getCurrentSkin(),skinName);
	}
}
