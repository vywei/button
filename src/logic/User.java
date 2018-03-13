package logic;

import java.util.List;
import java.util.ArrayList;
import armdb.QueryResult;
import armdb.SQLQueryException;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

//User observes: Button, Store
//User is subject of: Button, UserRoster, Database(?)
public class User implements Subject, Observer {
    private ArrayList<Observer> observers;
    private String password;
    private String username;
    private int ID;
    private int score;
    private List<Item> items;
    private Skin currentSkin;

    public User() {
	observers = new ArrayList<Observer>();
	items = new ArrayList<>();
    }
  
    public User(String newUsername, String newPassword) {
	username = validateUsername(newUsername);
	password = validatePassword(newPassword);
	observers = new ArrayList<>();
	items = new ArrayList<>();
    }
    
    public User(String username, int ID, int score) {
      this.username = username;
      this.ID = ID;
      this.score = score;
      observers = new ArrayList<>();
      items = new ArrayList<>();
  	  currentSkin = new Skin(2, "Basic Red Skin", 0, "red_button_unpressed.png", "red_button_pressed.png", "click.mp3");
    }

    public User(String newUsername, int newId, String newPassword, int newScore) {
	username = validateUsername(newUsername);
	setId(newId);
	password = validatePassword(newPassword);
	setScore(newScore);
	observers = new ArrayList<>();
	items = new ArrayList<>();
    }
  
    /**
     * For adding a new skin. Maybe unnecessary. Probably needs error checking
     * @param newSkin
     */
    public void addNewSkin(Skin newSkin) {
	Skin tempSkin = validateSkin(newSkin);
	if (tempSkin != null) {
		items.add(newSkin);
	}
    }
    
    public void updateItems() {
    	items = Main.getShop().getOwnedItems(this);
    }

    /**
     * Button class needs to be alerted when the current skin is changed.
     * If newSkin is valid, then currentSkin is set to that validated String and
     * observers are notified. Otherwise nothing happens and the currentSkin is
     * unaltered.
     * @param newSkin
     */
    public void changeSkin(Skin newSkin) {
	Skin tempSkin = validateSkin(newSkin);
		if (tempSkin != null) {
		    currentSkin = tempSkin;
		    LandingView landingView = new LandingView();
	        GridPane landingGrid = Main.gridSetup(landingView.getView());     
	        Main.landing = new Scene(landingGrid, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		    notifyObservers();
		}
    }

    /**
     * Rename of getScore
     * @return current score value
     */
    public int getBalance() {
	return getScore();
    }

    /**
     * rename of getScore
     * @return current score value
     */
    public int getCurrentScore(){
	return getScore();
    }
    /**
     * public getter for current skin
     * @return name of currently equipped skins
     */
    public Skin getCurrentSkin(){
	return currentSkin;
    }

    /**
     * public getter for ID of user
     * @return user ID
     */
    public int getID() {
	return ID;
    }

    /**
     * public getter for observer list
     * (For User observers should be Button and UserRoster)
     * @return array of Observers
     */
    public ArrayList<Observer> getObservers() {
	return observers;
    }

    /**
     * public getter for User password
     * @return password
     */
    public String getPassword() {
	return password;
    }

    /**
     * public getter for score
     * @return current score
     */
    public int getScore() {
	return score;
    }

    /**
     * Public getter for ArrayList of item names
     * @return list of all purchased items for this user
     */
    public List<Item> getItems() {
	return items;
    }

    /**
     * public getter for getting the user's username
     * @return username
     */
    public String getUsername() {
	return username;
    }

    /**
     * Only allows score to be increased by positive values.
     * @param x
     */
    public void increaseScore(int x) {
	if (x > 0) {
	    score += x;
	    System.out.println("adding " + x + ", score: " + score);
	    notifyObservers();
	}
    }

    /**
     * Notifies all observing object (Button and User Roster)
     * that User class has changed and that they should update
     * themselves with the changes.
     */
  @Override
  public void notifyObservers() {
      for (int i = 0; i < observers.size(); i++) {
	  observers.get(i).update();
      }
  }

    /**
     * Add another object to list of observers
     */
  @Override
  public void register(Observer observ) {
      observers.add(observ);
  }

    /**
     * public setter for the current skin
     * @param currentSkin
     */
    public void setCurrentSkin(Skin newSkin) {
	currentSkin = validateSkin(newSkin);
    }

    /**
     * public setter for the user's ID
     * @param newID
     */
    public void setId(int newId) {
	if (newId >= 0) {
	    ID = newId;
	}
    }

    /**
     * public setter for password
     * @param newPassword
     */
    public void setPassword(String newPassword) {
	password = validatePassword(newPassword);
    }

    /**
     * the konami code
     * Only allows valid (nonnegative) scores
     * @param newScore
     */
    public void setScore(int newScore) {
	if (newScore >= 0) {
	    score = newScore;
	}
    }

    /**
     * public setter for items
     * @param newSkins
     */
    public void setItems(List<Item> newItems) {
	Item tempItem;
	for (int i = 0; i < newItems.size(); i++) {
	    tempItem = newItems.get(i);
	    if (tempItem.getType() == Item.SKIN) {
		    if (validateSkin((Skin)tempItem) == null) {
		    	return;
		    }
	    }
	}
	items = newItems;
    }

    /**
     * public setter for username
     * @param newUsername
     */
    public void setUsername(String newUsername) {
	username = validateUsername(newUsername);
    }

    /**
     * Subtracting from score by a certain amount
     * Only allows positive numbers to be subtracted
     * Won't allow an amount larger than the score to be subtracted
     * @param amt
     */
    public void subtractFromBalance(int amt) {
	if (amt >= 0 && amt < score) {
	    score -= amt;
	    Database db = Database.getDatabase();
	    db.updateUserScore(this);
	}
    }

    /**
     * Unregister an object from observing this class
     * @param observ the object being unregistered
     */
  @Override
  public void unregister(Observer observ) {
      observers.remove(observ);
  }

    /**
     * User will be updated if Button or if Store is updated
     */
  @Override
  public void update() {
  }

    /**
     * User will be updated if Button or Store is updated
     * @param type the type of update. Maybe "B204" to be update score by 
     * 204 and "S2304" to add skin 2304?
     */
  @Override
  public void update(String type) {
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

    /**
     * Making sure that a skin that hasn't been purchased can't be equipped
     * @param newSkin
     * @return newSkin if valid, null if invalid
     */
    private Skin validateSkin(Skin newSkin) {
	for (int i = 0; i < items.size(); i++) {
	    if (items.get(i).equals(newSkin)) {
		return newSkin;
	    }
	}
	return null;
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

  @Override
  public void update(int amount) {
      increaseScore(amount);
      System.out.println("amount: " + amount);
  }

  public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(username);
      sb.append("\t\t\t");
      sb.append(score);
      return sb.toString();
  }

}
