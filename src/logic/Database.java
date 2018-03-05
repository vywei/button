package logic;

import java.util.ArrayList;
import armdb.ConnectHost;             //import to make connection
import armdb.QueryResult;
import armdb.SQLQuery;
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

  
}
