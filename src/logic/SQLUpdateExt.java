package logic;

import java.util.List;
import armdb.ConnectHost;
import armdb.SQLUpdate;
import armdb.SQLUpdateException;

public class SQLUpdateExt extends SQLUpdate {
    
  public SQLUpdateExt(ConnectHost con) 
  {
    super(con);
  }
  
  public int result(String table, List<String> columns, List<String> values, String constraint) throws SQLUpdateException 
  {
    
    // Initiate update query
    StringBuilder tempQuery = new StringBuilder("UPDATE " + table + " SET ");
    
    // Verify arguments are valid
    if (columns.size() != values.size()) 
    {
      throw new SQLUpdateException("Column count must match value count.");
    }
    
    // Add list of values to set
    for (int i = 0; i < columns.size(); i++) 
    {
      tempQuery.append(columns.get(i) + " ='" + values.get(i) + "',");
    }
    tempQuery.deleteCharAt(tempQuery.length() - 1);
    
    // Add constraints to query
    tempQuery.append(" ");
    tempQuery.append(constraint);
    
    return this.statement(tempQuery.toString());
  }
  
}