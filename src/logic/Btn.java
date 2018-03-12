package logic;

import java.util.ArrayList;

public class Btn extends javafx.scene.control.Button implements Subject, Observer {
    private Skin currentSkin;
    private Boolean isPressed;
    private User currentUser;
    private int buttonWeight;
    private ArrayList<Observer> observers;
        
    /**
     * Button constructor, set current skin and weight of the button
     * @param currentSkin, weight
     */
    public Btn(Skin currentSkin, int weight, String label) {
      super(label);
      this.currentSkin = currentSkin;
    	  this.isPressed = false;
    	  this.buttonWeight = weight;
    	  observers = new ArrayList<>();
    	  User temp = Main.getUser();
    	  this.register((Observer)temp);
    }
    /**
     * getter method for whether or not the button is pressed
     *
     */
    public boolean getPressedStatus() {
    		return isPressed;
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

    	for (int i = 0; i < observers.size(); i++) {
    	    User temp = (User)observers.get(i);
    	    if (temp.getCurrentSkin().equals(this.currentSkin)) {
    	    		this.currentSkin = temp.getCurrentSkin();
    	    }
    	}
    }
    
    @Override
    public void update(String type) {
    	//No-Op
    }
    
    @Override
    public void register(Observer observ) {
	if (observ != null) {
	    observers.add(observ);
	    currentUser = (User)observ;
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
	  System.out.println("updating button's observers");
    }
    
    @Override
    public void update(int amount) {
	//No-Op
      
    }
    
}
