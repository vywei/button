package logic;

import logic.Database;

public class Settings {
	public static final int LOW = 0;
	public static final int MED = 1;
	public static final int HIGH = 2;
	public static final int DISABLED = 0;
	public static final int ENABLED = 1;
	
	private int videoResWidth;
	private int videoResHeight;
	private int textureQual;
	private int effectsQual;
	private int audioEnabled;
	private int musicVol;
	private int effectsVol;
	private String musicPath;
	
	private int smellVariable;
	
	private Database db;
	
	public Settings() {
	  
	}
	
	public Settings(User u) {
	    db = Database.getDatabase();
	    
	    db.getSettings(u, this);
	}
	
	public void saveSettings(User u) {
		db.saveSettings(u, this);
	}

	public int getVideoResWidth() {
		return videoResWidth;
	}


	public void setVideoResWidth(int videoResWidth) {
		this.videoResWidth = videoResWidth;
	}
	
	public int getVideoResHeight() {
		return videoResHeight;
	}


	public void setVideoResHeight(int videoResHeight) {
	  this.videoResHeight = videoResHeight;
	}

	public int getTextureQual() {
		return textureQual;
	}

	public void setTextureQual(int textureQual) {
		if (textureQual == LOW || textureQual == MED || textureQual == HIGH) {
		  this.textureQual = textureQual;
		}
	}

	public int getEffectsQual() {
		return effectsQual;
	}

	public void setEffectsQual(int effectsQual) {
		if (effectsQual == LOW || effectsQual == MED || effectsQual == HIGH) {
		  this.effectsQual = effectsQual;
		}
	}

	public int getAudioEnabled() {
		return audioEnabled;
	}

	public void setAudioEnabled(int audioEnabled) {
		if (audioEnabled == ENABLED || audioEnabled == DISABLED) {
		  this.audioEnabled = audioEnabled;
		}
	}

	public int getMusicVol() {
		return musicVol;
	}

	public void setMusicVol(int musicVol) {
		if (musicVol >= 0 && musicVol <= 100) {
		  this.musicVol = musicVol;
		}
	}

	public int getEffectsVol() {
		return effectsVol;
	}

	public void setEffectsVol(int effectsVol) {
		if (effectsVol >= 0 && effectsVol <= 100) {
		  this.effectsVol = effectsVol;
		}
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
	  this.musicPath = musicPath;
	}
	  
}
