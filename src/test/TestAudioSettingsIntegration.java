package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.BugReport;
import logic.User;
import logic.Settings;

public class TestAudioSettingsIntegration {

	@Test
	public void testAudioIntegration() {
		boolean result = false;
		User testUser = new User("testuser", "testpassword");
		Settings s = new Settings(testUser);
		s.setVideoResHeight(5);
		s.setVideoResWidth(10);
		s.setEffectsQual(7);
		s.setAudioEnabled(2);
		s.setMusicPath("/home/Baby.mp3");
		int heightResult = s.getVideoResHeight();
		int widthResult = s.getVideoResWidth();
		int effectsResult = s.getEffectsQual();
		int audioResult = s.getAudioEnabled();
		String musicResult = s.getMusicPath();
		if (heightResult == 5 &&
				widthResult == 10 &&
				effectsResult == 0 && 
				audioResult == 0 &&
				musicResult.equals("/home/Baby.mp3")) {
			result = true;
		}
		assertEquals(true,result);
	}


}
