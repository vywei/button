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
	sb.append("DavideFalessi\t\t\t15789463");
	sb.append("\n");
	sb.append("2");
	sb.append("\t\t\t");
	sb.append("LebronJames\t\t\t15789462");
	sb.append("\n");
	sb.append("3");
	sb.append("\t\t\t");
	sb.append("testuser\t\t\t1801");
	sb.append("\n");
	sb.append("4");
	sb.append("\t\t\t");
	sb.append("TeamStreaker\t\t\t1524");
	sb.append("\n");
	sb.append("5");
	sb.append("\t\t\t");
	sb.append("BioWars\t\t\t542");
	sb.append("\n");
	sb.append("6");
	sb.append("\t\t\t");
	sb.append("CannonBall\t\t\t13");
	sb.append("\n");
	sb.append("7");
	sb.append("\t\t\t");
	sb.append("TeamSimternship\t\t\t5");
	sb.append("\n");
	sb.append("8");
	sb.append("\t\t\t");
	sb.append("TeamLemonomics\t\t\t1");
	sb.append("\n");
	sb.append("1");
	sb.append("\t\t\t");
	sb.append("cory\t\t\t0");
	sb.append("\n");
	String expected = sb.toString();
	System.out.println("output: " + output + "\n expected: " + expected);
	//assertTrue(output.equalsIgnoreCase(expected));
    
    }
}
