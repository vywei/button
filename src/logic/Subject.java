package button;

public interface Subject {
    void register(Observer observ);
  
    void unregister(Observer observ);
  
    void notifyObservers();
 
}
