package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import armdb.ConnectHost;             //import to make connection
import armdb.QueryResult;
import armdb.SQLQueryException;
import armdb.SQLUpdateException;

public class Database {
	public static final String PLAYER_TABLE = "player";
	public static final String SCORE_COLUMN = "score";
	public static final String USERNAME_COLUMN = "username";
	public static final String CUR_SKIN_COLUMN = "current_skin";
	public static final String WHERE_ID = "WHERE id = ";
	private static final Logger LOGGER = Logger.getLogger(Database.class.getName());
	
    ConnectHost ch;
    private static Database db;
  
  private Database() {
    ch = loadDatabaseCredentials();
  }
  
  public static Database getDatabase() {
  if (db == null) {
      db = new Database();
  }
  return db;
  }
  
  private ConnectHost loadDatabaseCredentials() {
    String fileURL = "";
    String host = "";
    String user = "";
    String pass = "";
    String dbName = "";
    String filename = "./bin/logic/dontPushThis.txt";
    
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      fileURL = br.readLine();
      host = br.readLine();
      user = br.readLine();
      pass = br.readLine();
      dbName = br.readLine();
    }
    catch (FileNotFoundException fnfe) {
      LOGGER.log( Level.SEVERE, fnfe.toString(), fnfe );
    }
    catch (IOException ioe) {
      LOGGER.log( Level.SEVERE, ioe.toString(), ioe );
    }
    
    return new ConnectHost(fileURL, host, user, pass, dbName);
  }
  
  public ConnectHost getCH() {
    return ch;
  }
  
  public String encryptPassword(String password) {
    String passHash = "";
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
      md.update(password.getBytes());
      byte[] digest = md.digest();
      passHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
    } catch (NoSuchAlgorithmException e) {
    	LOGGER.log( Level.SEVERE, e.toString(), e );
    }
    
    return passHash;
  }
  
  public User loginUser(String username, String password) {
    User resultUser = null;
    
    String passHash = encryptPassword(password);
    
    // Create query and result
    SQLSelect query = new SQLSelect(ch);
    QueryResult qr;

    // Execute the query
    try {
    	ArrayList<String> cols = new ArrayList<>();
    	cols.add("player.id AS pid");
    	cols.add(USERNAME_COLUMN);
    	cols.add(SCORE_COLUMN);
    	cols.add(CUR_SKIN_COLUMN );
    	cols.add("name");
    	cols.add("image");
    	cols.add("image_pressed");
    	cols.add("price_points");
    	cols.add("sound");
    	
        qr = query.result(PLAYER_TABLE + " JOIN item ON current_skin = item.id", cols, 
            "WHERE username = '" + username + 
            "' AND password = '" + passHash + "'");

        // Convert each row into an item
        resultUser = parseUser(qr);
    }
    catch(SQLQueryException e){
    	LOGGER.log( Level.SEVERE, e.toString(), e );
    }
    
    if (resultUser != null) {
      return resultUser;
    }
    else {
      return null;
    }
  }
  
  public void updateUserScore(User u) {
    // Create query and result
    SQLUpdateExt query = new SQLUpdateExt(ch);
    
    // Execute the query
    try {
      ArrayList<String> cols = new ArrayList<>();
      cols.add(SCORE_COLUMN);
      ArrayList<String> vals = new ArrayList<>();
      vals.add(Integer.toString(u.getScore()));
      String constraint = WHERE_ID + Integer.toString(u.getID());
      
      query.result(PLAYER_TABLE, cols, vals, constraint); 
    }
    catch(SQLUpdateException e){                   
    	LOGGER.log( Level.SEVERE, e.toString(), e );         
    }
  }
  
  public void updateUserSkin(User u) {
	    // Create query and result
	    SQLUpdateExt query = new SQLUpdateExt(ch);
	    
	    // Execute the query
	    try {
	      ArrayList<String> cols = new ArrayList<>();
	      cols.add(CUR_SKIN_COLUMN);
	      ArrayList<String> vals = new ArrayList<>();
	      vals.add(Integer.toString(u.getCurrentSkin().getID()));
	      String constraint = WHERE_ID + Integer.toString(u.getID());
	      
	      query.result(PLAYER_TABLE, cols, vals, constraint); 
	    }
	    catch(SQLUpdateException e){                   
	    	LOGGER.log( Level.SEVERE, e.toString(), e );         
	    }
	  }
  
  private void parsePlayers(QueryResult qr, List<User> list) {
    // Loop through all rows in the result
    while(qr.nextFlag()) {
      
      // Parse the column data
      int id = Integer.parseInt(qr.getValue("id"));
      String username = (qr.getValue(USERNAME_COLUMN));
      int score = Integer.parseInt(qr.getValue(SCORE_COLUMN));
      
      //Instantiate new item and insert into result list
      User newUser = new User(username, id, score);
      list.add(newUser);
    }
  }
  
  private User parseUser(QueryResult qr) {
    // Loop through all rows in the result
    User newUser = null;
    
    while(qr.nextFlag()) {
      
      // Parse the column data
      int id = Integer.parseInt(qr.getValue("pid"));
      String username = (qr.getValue(USERNAME_COLUMN));
      int score = Integer.parseInt(qr.getValue(SCORE_COLUMN));
      int sid = Integer.parseInt(qr.getValue(CUR_SKIN_COLUMN));
      String name = (qr.getValue("name"));
      int price = Integer.parseInt(qr.getValue("price_points"));
      String image = qr.getValue("image");
      String imagePressed = qr.getValue("image_pressed");
      String sound = qr.getValue("sound");
      
      //Instantiate new item and insert into result list
      newUser = new User(username, id, score, new Skin(sid, name, price, image, imagePressed, sound));
    }
    if (newUser != null) {
      return newUser;
    }
    else {
      return null;
    }
  }
    
    
  public List<User> getLeaderboard() {
	// Create result list
	List<User> players = new ArrayList<>();

	// Create query and result
	SQLSelect query = new SQLSelect(ch);
	QueryResult qr;

	// Execute the query
	try {
	    qr = query.result(PLAYER_TABLE, new ArrayList<String>(), "ORDER BY score DESC LIMIT 10");

	    // Convert each row into an item
	    parsePlayers(qr, players);
	}
	catch(SQLQueryException e){
		LOGGER.log( Level.SEVERE, e.toString(), e );
	}

	return players;
    }
  
  public void getSettings(User u) {
	
	SQLSelect query = new SQLSelect(ch);
	QueryResult qr;
	
	try {
	    qr = query.result("settings", new ArrayList<String>(), "WHERE user_id = " + u.getID());
	
	    while (qr.nextFlag()) {
	    	int temp = Integer.parseInt(qr.getValue("vid_res_width"));
	        Settings.setVideoResWidth(temp);
	        temp = Integer.parseInt(qr.getValue("vid_res_height"));
	        Settings.setVideoResHeight(temp);
	        temp = Integer.parseInt(qr.getValue("vid_textures"));
	        Settings.setTextureQual(temp);
	        temp = Integer.parseInt(qr.getValue("vid_effects"));
	        Settings.setEffectsQual(temp);
	        temp = Integer.parseInt(qr.getValue("audio"));
	        Settings.setAudioEnabled(temp);
	        temp = Integer.parseInt(qr.getValue("music_vol"));
	        Settings.setMusicVol(temp);
	        temp = Integer.parseInt(qr.getValue("effects_vol"));
	        Settings.setEffectsVol(temp);
	        String stemp = qr.getValue("music");
	        Settings.setMusicPath(stemp);
	    }
	}
	catch(SQLQueryException e){
		LOGGER.log( Level.SEVERE, e.toString(), e );
	}
	
  }
  
  public void saveSettings(User u, Settings s) {
    SQLUpdateExt query = new SQLUpdateExt(ch);
    
    try {
      ArrayList<String> cols = new ArrayList<>();
      cols.add("vid_res_width");
      cols.add("vid_res_height");
      cols.add("vid_textures");
      cols.add("vid_effects");
      cols.add("audio");
      cols.add("music_vol");
      cols.add("effects_vol");
      cols.add("music");
      
      ArrayList<String> vals = new ArrayList<>();
      vals.add(Integer.toString(s.getVideoResWidth()));
      vals.add(Integer.toString(s.getVideoResHeight()));
      vals.add(Integer.toString(s.getTextureQual()));
      vals.add(Integer.toString(s.getEffectsQual()));
      vals.add(Integer.toString(s.getAudioEnabled()));
      vals.add(Integer.toString(s.getMusicVol()));
      vals.add(Integer.toString(s.getEffectsVol()));
      vals.add(s.getMusicPath());
      
      String constraint = WHERE_ID + Integer.toString(u.getID());
      
      query.result("settings", cols, vals, constraint); 
    }
    catch(SQLUpdateException e){                   
        LOGGER.log( Level.SEVERE, e.toString(), e );          
    }
  }



}
