package logic;

public abstract class Item {
  public static final int BASE_ITEM = 0;
  public static final int SKIN = 1;
  
  protected int id;
  protected String name;
  protected int price;
  protected String image;
  
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
	  return BASE_ITEM;
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
