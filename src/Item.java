public class Item {
  private int id;
  private String name;
  private int price;
  
  public Item(int id, String name, int price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }
  
  public int getPrice(){
	  return price;
  }
}
