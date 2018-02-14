package logic;

import java.util.ArrayList;

//User observes: Button, Store
//User is subject of: Button, UserRoster, Database(?)
public class User implements Subject, Observer {
    private ArrayList<Observer> observers;
    private String password;
    private String username;
    private int ID;
    private int score;
    private ArrayList<String> skins;
    private String currentSkin;
  
    public User(String newUsername, String newPassword) {
	username = validateUsername(newUsername);
	password = validatePassword(newPassword);
    }
  
    /**
     * Validates that the given username actually fits the parameters.
     * @param newUsername
     * @return newUsername if valid, null if invalid
     */
    private String validateUsername(String newUsername) {
	if (newUsername.length() > 6) {
	    return newUsername;
	}
	//if newUsername isn't in database, return null
	return null;
    }
    /**
     * public getter for current skin
     */
    public String getCurrentSkin(){
    	return currentSkin;
    }
    /**
     * public getter for current score
     */
    public int getCurrentScore(){
    	return score;
    }
    /**
     * Validates that the given password fits parameters
     * @param newPassword
     * @return newPassword if valid, null if invalid
     */
    private String validatePassword(String newPassword) {
	if (newPassword.length() > 8) {
	    return newPassword;
	}
	//if newPassword doesn't match for the name in database
	//if password doesn't contain certain characters
	return null;
    }
  
  @Override
  public void register(Observer observ) {
      observers.add(observ);
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public void setID(int ID) {
    this.ID = ID;
  }
  
  public int getID() {
    return this.ID;
  }

  @Override
  public void unregister(Observer observ) {
      observers.remove(observ);
  }

  @Override
  public void notifyObservers() {
      for (int i = 0; i < observers.size(); i++) {
	  observers.get(i).update();
      }
  }
  
    /**
     * Button class needs to be alerted when the current skin is changed.
     * If newSkin is valid, then currentSkin is set to that validated String and
     * observers are notified. Otherwise nothing happens and the currentSkin is
     * unaltered.
     * @param newSkin
     */
    public void changeSkin(String newSkin) {
	String tempSkin = validateSkin(newSkin);
	if (tempSkin != null) {
	    currentSkin = tempSkin;
	    notifyObservers();
	}
    }

    /**
     * Making sure that a skin that hasn't been purchased can't be equipped
     * @param newSkin
     * @return newSkin if valid, null if invalid
     */
    public String validateSkin(String newSkin) {
	for (int i = 0; i < skins.size(); i++) {
	    if (skins.get(i).equals(newSkin)) {
		return newSkin;
	    }
	}
	return null;
    }
  
    /**
     * Only allows score to be increased by positive values.
     * @param x
     */
    public void increaseScore(int x) {
	if (x > 0) {
	    score += x;
	}
    }
  
    /**
     * For adding a new skin. Maybe unnecessary. Probably needs error checking
     * @param newSkin
     */
    public void addNewSkin(String newSkin) {
	skins.add(newSkin);
    }
    
    public int getBalance() {
      return this.score;
    }
    
    public void subtractFromBalance(int amt) {
      this.score -= amt;
    }

  @Override
  public void update() {
      // TODO Auto-generated method stub
    
  }

  @Override
  public void update(User user, String type) {
      // TODO Auto-generated method stub
    
  }
  
}
