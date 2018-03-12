package logic;

public class Skin extends Item{
	
	private String imagePressed;
	private String sound;
	
    public Skin(int id, String name, int price, String image, String imagePressed, String sound) {
	    this.id = id;
	    this.name = name;
	    this.price = price;
	    this.image = image;
	    this.imagePressed = imagePressed;
	    this.sound = sound;
    }
    
    public String getSound() {
      return sound;
    }
    
    public String getImagePressed() {
    	return imagePressed;
    }
    
    @Override
    public int getType() {
  	  return SKIN;
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
         
        return i.id == this.id;
    }
}
