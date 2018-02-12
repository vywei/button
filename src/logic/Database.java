package logic;

import armdb.ConnectHost;             //import to make connection

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
  
}
