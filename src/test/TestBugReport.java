package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.BugReport;
import logic.User;

public class TestBugReport {

	@Test
	public void testBugReport() {
		String reportText = "Points did not increment after pressing button.";
		User testUser = new User("testuser", "testpassword");
		BugReport br = new BugReport(testUser, reportText, "blank_email");
		assertEquals(br.getReportMessage(), reportText);
	}

}
