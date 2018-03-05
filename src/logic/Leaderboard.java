package logic;

import java.util.ArrayList;

//Leaderboard observes: Database
//Leaderboard is subject of: Database
public class Leaderboard implements Subject, Observer {
    private ArrayList<Observer> observers;
    private ArrayList<User> users;
    private static Leaderboard leaderboard;
    private static Database db;
  
    private Leaderboard() {
	observers = new ArrayList<Observer>();
	users = new ArrayList<User>();
	db = db.getDatabase();
    }
  
    public static Leaderboard getMaster() {
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
  }

  @Override
  public void update(int amount) {
  }

}
