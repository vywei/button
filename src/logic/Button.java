package logic;

import java.util.ArrayList;

public class Button implements Subject, Observer {
    private Skin currentSkin;
    private Boolean isPressed;
    private User currentUser;
    private int buttonWeight;
    private ArrayList<Observer> observers;
        
    /**
     * Button constructor, set current skin and weight of the button
     * @param currentSkin, weight
     */
    public Button(Skin currentSkin, int weight) {
	this.currentSkin = currentSkin;
	this.isPressed = false;
	this.buttonWeight = weight;
    }
    /**
     * increases the current user's score by the worth of the button
     *
     */
    public void increaseScore() {
        notifyObservers();
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
    public Skin getCurrentSkin(){
      return currentSkin;
    }
    /**
     * sets the buttons skin
     * @param newSkin
     */
    public void setSkin(Skin newSkin){
      this.currentSkin = newSkin;
    }
    @Override
    public void update() {
	// TODO Auto-generated method stub 
    }
    
    @Override
    public void update(String type) {
	// TODO Auto-generated method stub
    }
    
    @Override
    public void register(Observer observ) {
	if (observ != null) {
	    observers.add(observ);
	}
    }
    
    @Override
    public void unregister(Observer observ) {
	if (observ != null) {
	    observers.remove(observ);
	}
    }
    
    @Override
    public void notifyObservers() {
	for (int i = 0; i < observers.size(); i++) {
	    observers.get(i).update(buttonWeight); //need to update with buttonweight
	}
    }
    
    @Override
    public void update(int amount) {
	// TODO Auto-generated method stub
      
    }
    
}
