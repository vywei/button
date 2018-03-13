package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.BugReport;
import logic.User;
import logic.Settings;

public class TestAudioSettings {

	@Test
	public void testAudioResHeight() {
		User testUser = new User("testuser", "testpassword");
		Settings s = new Settings(testUser);
		s.setVideoResHeight(5);
		int result = s.getVideoResHeight();
		assertEquals(5,result);
	}
	
	@Test
	public void testAudioResWidth() {
		User testUser = new User("testuser", "testpassword");
		Settings s = new Settings(testUser);
		s.setVideoResWidth(5);
		int result = s.getVideoResWidth();
		assertEquals(5,result);
	}

}
