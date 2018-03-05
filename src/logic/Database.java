package logic;

import java.util.ArrayList;
import java.util.List;
import armdb.ConnectHost;             //import to make connection
import armdb.QueryResult;
import armdb.SQLQueryException;

public class Database {
  ConnectHost ch;
  
  public Database() {
    // Omitted for push to git
    String fileURL = "";  
    String host = "";                             
    String user = "";                                          
    String pass = "";                                     
    String dbName = ""; 
    
    ch = new ConnectHost(fileURL, host, user, pass, dbName);
  }
  
  public ConnectHost getCH() {
    return ch;
  }
  
  /**
   * Updates user score in the database
   * @param u
   */
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
      
      query.result("player", cols, vals, constraint); 
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
    
  public List<User> getLeaderboard() {
    // Create result list
    List<User> players = new ArrayList<>();
    
    // Create query and result
    SQLSelect query = new SQLSelect(ch);
    QueryResult qr;  
    
    // Execute the query
    try {
      qr = query.result("player", new ArrayList<String>(), "ORDER BY score DESC LIMIT 10"); 

      // Convert each row into an item
      parsePlayers(qr, players);
    }
    catch(SQLQueryException e){                   
        System.out.println(e.getMessage());           
    }
    
    return players;
  }


  
}
