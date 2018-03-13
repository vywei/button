package logic;

import logic.Database;

public class Settings {
	public static final int LOW = 0;
	public static final int MED = 1;
	public static final int HIGH = 2;
	public static final int DISABLED = 0;
	public static final int ENABLED = 1;
	
	private static int videoResWidth;
	private static int videoResHeight;
	private static int textureQual;
	private static int effectsQual;
	private static int audioEnabled;
	private static int musicVol;
	private static int effectsVol;
	private static String musicPath;
	
	private Database db;
	
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


	public static void setVideoResWidth(int videoResWidth) {
		Settings.videoResWidth = videoResWidth;
	}
	
	public int getVideoResHeight() {
		return videoResHeight;
	}


	public static void setVideoResHeight(int videoResHeight) {
		Settings.videoResHeight = videoResHeight;
	}

	public int getTextureQual() {
		return textureQual;
	}

	public static void setTextureQual(int textureQual) {
		if (textureQual == LOW || textureQual == MED || textureQual == HIGH) {
			Settings.textureQual = textureQual;
		}
	}

	public int getEffectsQual() {
		return effectsQual;
	}

	public static void setEffectsQual(int effectsQual) {
		if (effectsQual == LOW || effectsQual == MED || effectsQual == HIGH) {
			Settings.effectsQual = effectsQual;
		}
	}

	public int getAudioEnabled() {
		return audioEnabled;
	}

	public static void setAudioEnabled(int audioEnabled) {
		if (audioEnabled == ENABLED || audioEnabled == DISABLED) {
			Settings.audioEnabled = audioEnabled;
		}
	}

	public int getMusicVol() {
		return musicVol;
	}

	public static void setMusicVol(int musicVol) {
		if (musicVol >= 0 && musicVol <= 100) {
			Settings.musicVol = musicVol;
		}
	}

	public int getEffectsVol() {
		return effectsVol;
	}

	public static void setEffectsVol(int effectsVol) {
		if (effectsVol >= 0 && effectsVol <= 100) {
			Settings.effectsVol = effectsVol;
		}
	}

	public String getMusicPath() {
		return musicPath;
	}

	public static void setMusicPath(String musicPath) {
		Settings.musicPath = musicPath;
	}
	  
}
