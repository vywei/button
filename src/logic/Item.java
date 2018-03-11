package logic;

public abstract class Item {
  public static final int ITEM = 0;
  public static final int SKIN = 1;
  
  public int id;
  public String name;
  public int price;
  public String image;
  
  //constructors are specific to Skin, SoundEffect, and VisualEffect
  //so future changes to specific constructors can be made
  
  public String getImage() {
	return image;
  }
  
  public int getID() {
    return id;
  }
  
  public String getName() {
    return name;
  }
  
  public int getPrice() {
    return price;
  }
  
  public int getType() {
	  return ITEM;
  }
  
  @Override
  public boolean equals(Object o) {
      if (o == this) {
          return true;
      }

      if (!(o instanceof Item)) {
          return false;
      }
       
      Item i = (Item) o;
       
      if (i.id == this.id) {
    	  return true;
      }
      return false;
  }
}
