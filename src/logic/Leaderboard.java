package logic;

import java.util.ArrayList;

//Leaderboard observes: Database
//Leaderboard is subject of: Database
public class Leaderboard implements Subject, Observer {
    private ArrayList<Observer> observers;
    private ArrayList<User> users;
    private static Leaderboard leaderboard;
    private Database db;
    private static final String brk =  "\t\t\t";
  
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
      sb.append(brk);
      sb.append("USERNAME");
      sb.append(brk);
      sb.append("SCORE");
      sb.append("\n");
      for (int i = 0; i < users.size(); i++) {
         sb.append(i + 1);
         sb.append(brk);
         sb.append(users.get(i).toString());
         sb.append("\n");
      }
      return sb.toString();
  }

}
