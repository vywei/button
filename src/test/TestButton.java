package test;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import org.junit.BeforeClass;

import logic.Btn;
import logic.Main;
import logic.Skin;
import logic.User;

import org.junit.Test;

import javafx.embed.swing.JFXPanel;

public class TestButton {

	 @BeforeClass
     public static void initToolkit() throws InterruptedException
     {
         final CountDownLatch latch = new CountDownLatch(1);
         SwingUtilities.invokeLater(() -> {
             new JFXPanel(); // initializes JavaFX environment
             latch.countDown();
         });

         if (!latch.await(5L, TimeUnit.SECONDS))
             throw new ExceptionInInitializerError();
     }
	
	@Test
	public void testButtonSetSkin() {
		Main.setUser(new User("testuser", 1, 0));
		Skin s = new Skin(0, "Test Skin", 500, "america_button_unpressed.png", "america_button_pressed.png", "click.mp3");
		Btn b = new Btn(s, 1, "Label");
		b.setSkin(s);
		assertEquals(b.getCurrentSkin(), s);
	}

}