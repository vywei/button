package logic;

public class Skin extends Item{
	
	private String imagePressed;
	
    public Skin(int id, String name, int price, String image, String imagePressed) {
	    this.id = id;
	    this.name = name;
	    this.price = price;
	    this.image = image;
	    this.imagePressed = imagePressed;
    }
    
    public String getImagePressed() {
    	return imagePressed;
    }
    
    @Override
    public int getType() {
  	  return SKIN;
    }
}
