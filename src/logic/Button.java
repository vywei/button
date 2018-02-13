package logic;

public class Button{
	Skin currentSkin;
	Boolean isPressed;
	
	public Button(Skin currentSkin) {
		this.currentSkin = currentSkin;
		this.isPressed = false;
	}
	public boolean isPressed(){
		return isPressed();
	}
	public Skin currentSkin(){
		return currentSkin;
	}
	public void setSkin(Skin currentSkin){
		this.currentSkin = currentSkin;
	}
}