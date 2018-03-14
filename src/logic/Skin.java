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
        
    @Override
    public int getPrice() {
      return price;
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

        if (!(o instanceof Skin)) {
            return false;
        }
         
        Skin s = (Skin) o;
         
        return s.id == this.id;
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + id;
        result = 31 * result + price;
        result = 31 * result + image.hashCode();
        result = 31 * result + imagePressed.hashCode();
        return result;
    }
}
