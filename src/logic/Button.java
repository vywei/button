package logic;

public class Button{
	String currentSkin;
	Boolean isPressed;
	User currentUser;
	int buttonWeight;
    /**
     * Button constructor, set current skin and weight of the button
     * @param currentSkin, weight
     */
	public Button(String currentSkin,int weight) {
		this.currentSkin = currentSkin;
		this.isPressed = false;
		this.buttonWeight = weight;
	}
    /**
     * increases the current user's score by the worth of the button 
     * 
     */
    public void increaseScore() {
    	currentUser.increaseScore(buttonWeight); 
    }
    /**
     * Getter gets the user's score. 
     * 
     */
    public int getUserScore(){
    	return currentUser.getCurrentScore();
    }
    /**
     * gets the current buttons skin
     * 
     */
	public String getCurrentSkin(){
		return currentSkin;
	}
    /**
     * sets the buttons skin
     * @param newSkin
     */
	public void setSkin(String newSkin){
		this.currentSkin = newSkin;
	}
}