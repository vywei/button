package test;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import logic.Btn;
import logic.BugReport;
import logic.User;
import logic.Settings;
import logic.Skin;
import logic.Leaderboard;
import logic.Main;

public class LeaderboardLoopTest {
	 @BeforeClass
     public static void initToolkit() throws InterruptedException
     {
         final CountDownLatch latch = new CountDownLatch(1);
         SwingUtilities.invokeLater(() -> {
             new JFXPanel(); // initializes JavaFX environment
             latch.countDown();
         });

         //if (!latch.await(5L, TimeUnit.SECONDS))
           //  throw new ExceptionInInitializerError();
     }
	
	@Test
	public void leaderboardTestLoopExecutedOnce() {
		Skin s = new Skin(5,"hi",1,"1","1","h");
		Btn b = new Btn(s,5,"myBtn");
		User testUser = new User("testuser", "testpassword");
		b.register(testUser);
		b.increaseScore();
		b.notifyObservers();
		assertEquals(b.getUserScore(),testUser.getCurrentScore());
	}
	@Test
	public void leaderboardTestLoopExecutedZeroTimes() {
		Skin s = new Skin(5,"hi",1,"1","1","h");
		Btn b = new Btn(s,5,"myBtn");
		User testUser = new User("testuser", "testpassword");
		b.increaseScore();
		b.notifyObservers();
		assertNotEquals(1,testUser.getCurrentScore());
	}
	
	@Test
	public void leaderboardTestLoopExecutedTwoTimes() {
		Skin s = new Skin(5,"hi",1,"1","1","h");
		Btn b = new Btn(s,5,"myBtn");
		User testUser = new User("testuser", "testpassword");
		User testUser2 = new User("testUser2","testPassword2");
		b.register(testUser);
		b.register(testUser2);
		b.increaseScore();
		b.notifyObservers();
		assertEquals(b.getUserScore(),testUser.getCurrentScore());
	}
	
	@Test
	public void leaderboardTestLoopExecutedTenTimes() {
		Skin s = new Skin(5,"hi",1,"1","1","h");
		Btn b = new Btn(s,5,"myBtn");
		User testUser0 = new User("testuser0", "testpassword0");
		User testUser1 = new User("testuser1", "testpassword1");
		User testUser2 = new User("testuser1", "testpassword1");
		User testUser3 = new User("testuser1", "testpassword1");
		User testUser4 = new User("testuser1", "testpassword1");
		User testUser5 = new User("testuser1", "testpassword1");
		User testUser6 = new User("testuser1", "testpassword1");
		User testUser7 = new User("testuser1", "testpassword1");
		User testUser8 = new User("testuser1", "testpassword1");
		User testUser9 = new User("testuser1", "testpassword1");
		b.register(testUser0);
		b.register(testUser1);
		b.register(testUser2);
		b.register(testUser3);
		b.register(testUser4);
		b.register(testUser5);
		b.register(testUser6);
		b.register(testUser7);
		b.register(testUser8);
		b.register(testUser9);
		
		b.increaseScore();
		b.notifyObservers();
		assertEquals(b.getUserScore(),testUser0.getCurrentScore());
	}
	


}
