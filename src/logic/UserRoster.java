package logic;

import java.util.ArrayList;

//UserRoster observes: User
//UserRoster is subject of: Database
public class UserRoster implements Subject, Observer {
    private ArrayList<Observer> observers;
    private ArrayList<User> users;
    //private Store store;
    //private Database db;
  
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
      // TODO Auto-generated method stub
    
  }

  @Override
  public void update(String type) {
      // TODO Auto-generated method stub
    
  }

  @Override
  public void update(int amount) {
      // TODO Auto-generated method stub
    
  }

}
