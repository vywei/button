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
		assertEquals(0,result);
	}
	
	@Test
	public void testAudioResWidth() {
		User testUser = new User("testuser", "testpassword");
		Settings s = new Settings(testUser);
		s.setEffectsQual(5);
		int result = s.getEffectsQual();
		assertEquals(0,result);
	}
	
	@Test
	public void testAudioEnabled() {
		User testUser = new User("testuser", "testpassword");
		Settings s = new Settings(testUser);
		s.setAudioEnabled(5);
		int result = s.getAudioEnabled();
		assertEquals(0,result);
	}
	
	@Test
	public void testMusicVol() {
		User testUser = new User("testuser", "testpassword");
		Settings s = new Settings(testUser);
		s.setMusicVol(5);
		int result = s.getMusicVol();
		assertEquals(5,result);
	}
	
	@Test
	public void testEffectsVol() {
		User testUser = new User("testuser", "testpassword");
		Settings s = new Settings(testUser);
		s.setEffectsVol(5);
		int result = s.getEffectsVol();
		assertEquals(5,result);
	}
	
	@Test
	public void testMusicPath() {
		User testUser = new User("testuser", "testpassword");
		Settings s = new Settings(testUser);
		s.setMusicPath("/home/OopsIDidItAgain.mp3");
		String result = s.getMusicPath();
		assertEquals("/home/OopsIDidItAgain.mp3",result);
	}
	
	


}