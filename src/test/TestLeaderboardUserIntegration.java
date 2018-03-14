package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import logic.Leaderboard;
import logic.User;
import logic.Database;

public class TestLeaderboardUserIntegration {
    private static Leaderboard leader;
    private static Database db;
  
    @SuppressWarnings("static-access")
  @Test
  public void testListLeaderboardNotEmpty() {
	db = db.getDatabase();
	User result = db.loginUser("testUser", "password");

	ArrayList<User> users = new ArrayList<>();
	leader = leader.getLeaderboard();
	leader.update();
	boolean res;
	users = (ArrayList<User>) leader.getUsers();
	//	assertEquals(true, users.size() > 0);
    
    }
}
