package test;

import static org.junit.Assert.*;
import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;
import org.junit.BeforeClass;
import logic.Btn;
import logic.Skin;
import logic.User;
import org.junit.Test;
import javafx.embed.swing.JFXPanel;

public class TestUserBtnIntegration {

    //Stole this from TestButton.java
  @BeforeClass
  public static void initToolkit() throws InterruptedException {
	final CountDownLatch latch = new CountDownLatch(1);
	SwingUtilities.invokeLater(() -> {
		new JFXPanel(); // initializes JavaFX environment
		latch.countDown();
	    });
    }

  @Test
  public void testUserBtnScoreIncreaseIntegration() {
      User newUser = new User("testyMcTest", "password123");

      Btn newButton = new Btn(new Skin(0, null, 0, null, null, null), 12345, "testy'sButton");
      newButton.register(newUser);
      newButton.increaseScore();

      assertEquals(12345, newUser.getScore(), 0);
  }

}
