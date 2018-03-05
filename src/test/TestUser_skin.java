package test;
import static org.junit.Assert.*;
import org.junit.Test;
import logic.Item;
import logic.User;
public class TestUser_skin {
	@Test
	public void TestUserScore(){
		User u = new User("username0","password0");
		u.increaseScore(5);
		int score = u.getCurrentScore();
		assertEquals(5,score);
	}
}
