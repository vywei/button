package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.BugReport;
import logic.User;


public class TestBugReportIntegration {

    @Test
    public void testBugReportIntegration() {
        boolean result = false;
        User testUser = new User("testuser", "testpassword");
        BugReport b = new BugReport(testUser, "test message", "email");
        
        User newUser = b.getUser();
        String message = b.getReportMessage();
        if (newUser.equals(testUser)  &&
                message.equals("test message")) {
            result = true;
        }
        assertEquals(true,result);
    }


}
