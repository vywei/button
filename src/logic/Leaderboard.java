package logic;

import java.util.ArrayList;

//Leaderboard observes: Database
//Leaderboard is subject of: Database
public class Leaderboard implements Subject, Observer {
    private ArrayList<Observer> observers;
    private ArrayList<User> users;
    private static Leaderboard leaderboard;
    private Database db;
    private static final String BRK =  "\t\t\t";
  
    private Leaderboard() {
    	observers = new ArrayList<>();
		users = new ArrayList<>();
		db = Database.getDatabase();
    }
  
    public static Leaderboard getLeaderboard() {
	if (leaderboard == null) {
	    leaderboard = new Leaderboard();
	}
	return leaderboard;
    }
  
  @Override
  public void register(Observer observ) {
      observers.add(observ);
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

  @Override
  public void update() { 
      reloadLeaderboard();
  }

    private void reloadLeaderboard() {
	ArrayList<User> newUsers = (ArrayList<User>) db.getLeaderboard();
	if (newUsers != null) {
	    users = newUsers;
	}
    }

  @Override
  public void update(String username) {
	  // No need to implement this
  }

  @Override
  public void update(int amount) {
	 // No need to implement this
  }

  public String listLeaderboard() {
      StringBuilder sb = new StringBuilder();
      sb.append("RANK");
      sb.append(BRK);
      sb.append("USERNAME");
      sb.append(BRK);
      sb.append("SCORE");
      sb.append("\n");
      for (int i = 0; i < users.size(); i++) {
         sb.append(i + 1);
         sb.append(BRK);
         sb.append(users.get(i).getUsername());
         int toPad = users.get(i).getUsername().length();
         for (int j = 0; j < 24 - toPad; j++) {
           sb.append(" ");
         }
         sb.append(users.get(i).getScore());
         sb.append("\n");
      }
      return sb.toString();
  }
  
  public String getLeftList()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("RANK");
    sb.append("\n");
    for (int i = 0; i < users.size(); i++) {
      sb.append(i + 1);
      sb.append("\n");
    }
    return sb.toString();
  }
  
  public String getCenterList()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("USERNAME");
    sb.append("\n");
    for (int i = 0; i < users.size(); i++) {
      sb.append(users.get(i).getUsername());
      sb.append("\n");
    }
    return sb.toString();
  }
  
  public String getRightList()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("SCORE");
    sb.append("\n");
    for (int i = 0; i < users.size(); i++) {
      sb.append(users.get(i).getScore());
      sb.append("\n");
    }
    return sb.toString();
  }

}
