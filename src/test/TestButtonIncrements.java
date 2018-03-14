package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.User;

public class TestButtonIncrements {
  
  @Test
  public void TestPlusOne() {
    User user = new User("dfalessi123", 50, 2);
    int score = user.getScore();
    user.increaseScore(1);
    assertEquals(score+1, user.getScore());
  }
  
  
  @Test
  //setScore should not allow setting the score to a negative number
  public void TestNegativeScore() {
    User user = new User("dfalessi123", 50, 2);
    int score = user.getScore();
    user.setScore(-1);
    assertEquals(score, user.getScore());
  }

}
