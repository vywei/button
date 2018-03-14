package test;

import static org.junit.Assert.*;
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

	User user = new User();
	result.setScore(1337);
	leader = leader.getLeaderboard();
	leader.update();
    
	String output = leader.listLeaderboard();
	StringBuilder sb = new StringBuilder();
	sb.append("RANK");
	sb.append("\t\t\t");
	sb.append("USERNAME");
	sb.append("\t\t\t");
	sb.append("SCORE");
	sb.append("\n");
	sb.append("1");
	sb.append("\t\t\t");
	sb.append("testUser\t\t\t1337");
	sb.append("\n");
	String expected = sb.toString();
	System.out.println("output: " + output + "\n expected: " + expected);
	assertTrue(output.equalsIgnoreCase(expected));
    
    }
}
