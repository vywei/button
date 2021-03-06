package logic;

import java.util.List;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.logging.Logger;

//User observes: Button, Store
//User is subject of: Button, UserRoster, Database(?)
public class User implements Subject, Observer 
{
    private ArrayList<Observer> observers;
    private String password;
    private String username;
    private int id;
    private int score;
    private List<Item> items;
    private Skin currentSkin;
    private static final Logger LOGGER = Logger.getLogger(Database.class.getName());
    private Settings settings;

    public User() 
    {
	observers = new ArrayList<>();
	items = new ArrayList<>();
    }
  
    public User(String newUsername, String newPassword) 
    {
	username = validateUsername(newUsername);
	password = validatePassword(newPassword);
	observers = new ArrayList<>();
	items = new ArrayList<>();
    }
    
    public User(String username, int id, int score) 
    {
      this.username = username;
      this.id = id;
      this.score = score;
      observers = new ArrayList<>();
      items = new ArrayList<>();
  	  currentSkin = new Skin(2, "Basic Red Skin", 0, "red_button_unpressed.png", "red_button_pressed.png", "click.mp3");
    }
    
    public User(String username, int id, int score, Skin skin) 
    {
        this.username = username;
        this.id = id;
        this.score = score;
        observers = new ArrayList<>();
        items = new ArrayList<>();
        currentSkin = skin;
        settings = new Settings(this);
      }

    public User(String newUsername, int newId, String newPassword, int newScore) 
    {
		username = validateUsername(newUsername);
		setId(newId);
		password = validatePassword(newPassword);
		setScore(newScore);
		observers = new ArrayList<>();
		items = new ArrayList<>();
    }
    
    public Settings getSettings() 
    {
      return settings;
    }
    
    public void setSettings(Settings s) {
      settings = s;
    }
  
    /**
     * For adding a new skin. Maybe unnecessary. Probably needs error checking
     * @param newSkin
     */
    public void addNewSkin(Skin newSkin)
    {
	Skin tempSkin = validateSkin(newSkin);
	if (tempSkin != null) {
		items.add(newSkin);
	}
    }
    
    public void updateItems() 
    {
    	items = Main.getShop().getOwnedItems(this);
    }

    /**
     * Button class needs to be alerted when the current skin is changed.
     * If newSkin is valid, then currentSkin is set to that validated String and
     * observers are notified. Otherwise nothing happens and the currentSkin is
     * unaltered.
     * @param newSkin
     */
    public void changeSkin(Skin newSkin) 
    {
	Skin tempSkin = validateSkin(newSkin);
		if (tempSkin != null) {
		    currentSkin = tempSkin;
		    Main.landingView.updateImages();
		    Main.db.updateUserSkin(this);
		    notifyObservers();
		}
    }

    /**
     * Rename of getScore
     * @return current score value
     */
    public int getBalance() 
    {
	return getScore();
    }

    /**
     * rename of getScore
     * @return current score value
     */
    public int getCurrentScore()
    {
	return getScore();
    }
    /**
     * public getter for current skin
     * @return name of currently equipped skins
     */
    public Skin getCurrentSkin()
    {
	return currentSkin;
    }

    /**
     * public getter for ID of user
     * @return user ID
     */
    public int getID() {
	return id;
    }

    /**
     * public getter for observer list
     * (For User observers should be Button and UserRoster)
     * @return array of Observers
     */
    public List<Observer> getObservers() 
    {
	return observers;
    }

    /**
     * public getter for User password
     * @return password
     */
    public String getPassword() 
    {
	return password;
    }

    /**
     * public getter for score
     * @return current score
     */
    public int getScore() 
    {
	return score;
    }

    /**
     * Public getter for ArrayList of item names
     * @return list of all purchased items for this user
     */
    public List<Item> getItems() 
    {
	return items;
    }

    /**
     * public getter for getting the user's username
     * @return username
     */
    public String getUsername()
    {
	return username;
    }

    /**
     * Only allows score to be increased by positive values.
     * @param x
     */
    public void increaseScore(int x)
    {
	if (x > 0) {
	    score += x;
	    LOGGER.log( Level.INFO, "adding {0}", x );
	    notifyObservers();
	}
    }

    /**
     * Notifies all observing object (Button and User Roster)
     * that User class has changed and that they should update
     * themselves with the changes.
     */
  @Override
  public void notifyObservers() 
  {
      for (int i = 0; i < observers.size(); i++) {
	  observers.get(i).update();
      }
  }

    /**
     * Add another object to list of observers
     */
  @Override
  public void register(Observer observ) 
  {
      observers.add(observ);
  }

    /**
     * public setter for the current skin
     * @param currentSkin
     */
    public void setCurrentSkin(Skin newSkin) 
    {
	currentSkin = validateSkin(newSkin);
    }

    /**
     * public setter for the user's ID
     * @param newID
     */
    public void setId(int newId) 
    {
	if (newId >= 0) {
	    id = newId;
	}
    }

    /**
     * public setter for password
     * @param newPassword
     */
    public void setPassword(String newPassword) 
    {
	password = validatePassword(newPassword);
    }

    /**
     * the konami code
     * Only allows valid (nonnegative) scores
     * @param newScore
     */
    public void setScore(int newScore) 
    {
	if (newScore >= 0) 
	{
	    score = newScore;
	}
    }

    /**
     * public setter for items
     * @param newSkins
     */
    public void setItems(List<Item> newItems) 
    {
    	items = newItems;
    }

    /**
     * public setter for username
     * @param newUsername
     */
    public void setUsername(String newUsername) 
    {
	username = validateUsername(newUsername);
    }

    /**
     * Subtracting from score by a certain amount
     * Only allows positive numbers to be subtracted
     * Won't allow an amount larger than the score to be subtracted
     * @param amt
     */
    public void subtractFromBalance(int amt) 
    {
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
  public void unregister(Observer observ)
  {
      observers.remove(observ);
  }

    /**
     * User will be updated if Button or if Store is updated
     */
  @Override
  public void update() 
  {
	  // No need to implement this
  }

    /**
     * User will be updated if Button or Store is updated
     * @param type the type of update. Maybe "B204" to be update score by 
     * 204 and "S2304" to add skin 2304?
     */
  @Override
  public void update(String type) 
  {
	  // No need to implement this
  }

    /**
     * Validates that the given password fits parameters
     * @param newPassword
     * @return newPassword if valid, null if invalid
   */
    public String validatePassword(String newPassword) 
    {
	if (newPassword.length() > 8) 
	{
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
    private Skin validateSkin(Skin newSkin) 
    {
	for (int i = 0; i < items.size(); i++) 
	{
	    if (items.get(i).equals(newSkin)) 
	    {
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
    private String validateUsername(String newUsername) 
    {
	if (newUsername.length() > 6) 
	{
	    return newUsername;
	}
	//if newUsername isn't in database, return null
	return null;
    }

  @Override
  public void update(int amount) 
  {
      increaseScore(amount);
      LOGGER.log(Level.INFO, "amount: {0}", amount);
  }

  public String toString() 
  {
      StringBuilder sb = new StringBuilder();
      sb.append(username);
      sb.append("\t\t\t");
      sb.append(score);
      return sb.toString();
  }

}
