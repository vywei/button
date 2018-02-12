package logic;

public interface Subject {
    void register(Observer observ);
  
    void unregister(Observer observ);
  
    void notifyObservers();
 
}
