package logic;

public class BugReport{
	private User reportUser;
	private String reportMessage;
    /**
     * BugReport constructor 
     * @param user, bug report message
     */
	private BugReport(User user, String message){
		reportMessage = message;
		reportUser = user;
	}
    /**
     * getter for the report message
     * 
     */
	private String getReportMessage() {
		return reportMessage;
	}
    /**
     * getter for the report user
     * 
     */
	private User getUser(){
		return reportUser;
	}
	
}