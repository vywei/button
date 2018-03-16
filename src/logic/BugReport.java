package logic;

public class BugReport{
	private User reportUser;
	private String reportMessage;
	private String email;
    /**
     * BugReport constructor 
     * @param user, bug report message
     */
	public BugReport(User user, String message, String email)
	{
		reportMessage = message;
		reportUser = user;
		this.email = email;
	}
    /**
     * getter for the report message
     * 
     */
	public String getReportMessage() 
	{
		return reportMessage;
	}
    /**
     * getter for the report user
     * 
     */
	public User getUser()
	{
		return reportUser;
	}
	
	public String getEmail()
	{
		return email;
	}
	
}