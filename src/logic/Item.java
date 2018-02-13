package logic;

public abstract class Item {
  public int id;
  public String name;
  public int price;
  
  //constructors are specific to Skin, SoundEffect, and VisualEffect
  //so future changes to specific constructors can be made
  
  public int getID() {
    return id;
  };
  
  public String getName() {
    return name;
  };
  
  public int getPrice() {
    return price;
  };
}
