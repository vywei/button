package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.BugReport;

public class TestBugReport {

	@Test
	public void testBugReport() {
		String reportText = "Points did not increment after pressing button.";
		BugReport br = new BugReport(reportText);
		assertEquals(br.getReportMessage(), reportText);
	}

}
