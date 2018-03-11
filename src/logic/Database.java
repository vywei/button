package logic;

import java.util.ArrayList;
import java.util.List;
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

public class Database {
	public static final String PLAYER_TABLE = "player";
	
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
      System.out.println("Unable to connect to database, could not find database credentials.");
    }
    catch (IOException ioe) {
      System.out.println("Unable to connect to database.");
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
      e.printStackTrace();
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
        qr = query.result(PLAYER_TABLE, new ArrayList<String>(), 
            "WHERE username = '" + username + 
            "' AND password = '" + passHash + "'");

        // Convert each row into an item
        resultUser = parseUser(qr);
    }
    catch(SQLQueryException e){
        System.out.println(e.getMessage());
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
    SQLUpdate query = new SQLUpdate(ch);
    
    // Execute the query
    try {
      ArrayList<String> cols = new ArrayList<>();
      cols.add("score");
      ArrayList<String> vals = new ArrayList<>();
      vals.add(Integer.toString(u.getScore()));
      String constraint = "WHERE id = " + Integer.toString(u.getID());
      
      query.result(PLAYER_TABLE, cols, vals, constraint); 
    }
    catch(SQLQueryException e){                   
        System.out.println(e.getMessage());           
    }
  }
  
  private void parsePlayers(QueryResult qr, List<User> list) {
    // Loop through all rows in the result
    while(qr.nextFlag()) {
      
      // Parse the column data
      int id = Integer.parseInt(qr.getValue("id"));
      String username = (qr.getValue("username"));
      int score = Integer.parseInt(qr.getValue("score"));
      
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
      int id = Integer.parseInt(qr.getValue("id"));
      String username = (qr.getValue("username"));
      int score = Integer.parseInt(qr.getValue("score"));
      
      //Instantiate new item and insert into result list
      newUser = new User(username, id, score);
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
	    System.out.println(e.getMessage());
	}

	return players;
    }
  
  public void getSettings(User u, Settings s) {
	
	SQLSelect query = new SQLSelect(ch);
	QueryResult qr;
	
	try {
	    qr = query.result("settings", new ArrayList<String>(), "WHERE user_id = " + u.getID());
	
	    while (qr.nextFlag()) {
	    	int temp = Integer.parseInt(qr.getValue("vid_res_width"));
	        s.setVideoResWidth(temp);
	        temp = Integer.parseInt(qr.getValue("vid_res_height"));
	        s.setVideoResHeight(temp);
	        temp = Integer.parseInt(qr.getValue("vid_textures"));
	        s.setTextureQual(temp);
	        temp = Integer.parseInt(qr.getValue("vid_effects"));
	        s.setEffectsQual(temp);
	        temp = Integer.parseInt(qr.getValue("audio"));
	        s.setAudioEnabled(temp);
	        temp = Integer.parseInt(qr.getValue("music_vol"));
	        s.setMusicVol(temp);
	        temp = Integer.parseInt(qr.getValue("effects_vol"));
	        s.setEffectsVol(temp);
	        String stemp = qr.getValue("music");
	        s.setMusicPath(stemp);
	    }
	}
	catch(SQLQueryException e){
	    System.out.println(e.getMessage());
	}
	
  }
  
  public void saveSettings(User u, Settings s) {
    SQLUpdate query = new SQLUpdate(ch);
    
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
      
      String constraint = "WHERE id = " + Integer.toString(u.getID());
      
      query.result("settings", cols, vals, constraint); 
    }
    catch(SQLQueryException e){                   
        System.out.println(e.getMessage());           
    }
  }



}
