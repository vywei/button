package logic;

public class BugReport{
	private User reportUser;
	private String reportMessage;
    /**
     * BugReport constructor, 
     * @param user, bug report message
     */
	private BugReport(User user, String message){
		reportMessage = message;
		reportUser = user;
	}
	
	private String getReportMessage() {
		return reportMessage;
	}
	
	private User getUser(){
		return reportUser;
	}
	
}