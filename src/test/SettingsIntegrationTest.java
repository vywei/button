package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.Settings;
import logic.User;

public class SettingsIntegrationTest {

  @Test public void TestSetingsIntegration()
  {
    User user = new User ("testuser", "test");
    Settings settings = new Settings(user);
    settings.setMusicVol(50);
    settings.saveSettings(user);
    int expected = 50;
    int actual = settings.getMusicVol();
    assertEquals(expected, actual, 0);
  }
}
