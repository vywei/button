package logic;

public class BugReport{
	private User reportUser;
	private String reportMessage;
    /**
     * BugReport constructor 
     * @param user, bug report message
     */
	public BugReport(User user, String message){
		reportMessage = message;
		reportUser = user;
	}
    /**
     * getter for the report message
     * 
     */
	public String getReportMessage() {
		return reportMessage;
	}
    /**
     * getter for the report user
     * 
     */
	public User getUser(){
		return reportUser;
	}
	
}