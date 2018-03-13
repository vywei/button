package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.BugReport;
import logic.User;
import logic.Settings;

public class TestVideoSettings {

	@Test
	public void testAudioResHeight() {
		User testUser = new User("testuser", "testpassword");
		Settings s = new Settings(testUser);
		s.setTextureQual(5);
		int result = s.getTextureQual();
		assertEquals(5,result);
	}
	
	@Test
	public void testAudioResWidth() {
		User testUser = new User("testuser", "testpassword");
		Settings s = new Settings(testUser);
		s.setEffectsQual(5);
		int result = s.getEffectsQual();
		assertEquals(5,result);
	}

}