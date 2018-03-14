package test;
import static org.junit.Assert.*;
import org.junit.Test;
import logic.Leaderboard;

public class TestLeaderboard {
    private static Leaderboard leader;
    private static Leaderboard leader2;
  
    @SuppressWarnings("static-access")
  @Test
  public void testListLeaderboardEmpty() {
	leader = leader.getLeaderboard();
	String output = leader.listLeaderboard();
	StringBuilder sb = new StringBuilder();
	sb.append("RANK");
	sb.append("\t\t\t");
	sb.append("USERNAME");
	sb.append("\t\t\t");
	sb.append("SCORE");
	sb.append("\n");
	String expected = sb.toString();
	System.out.println("output: " + output + "\n expected: " + expected);
	assertTrue(output.equalsIgnoreCase(expected));
    }
  
    @SuppressWarnings("static-access")
  @Test
  public void testLeaderboardSingleton() {
	leader = leader.getLeaderboard();
	leader2 = leader.getLeaderboard();
	assertEquals(leader, leader2);
    }
}
