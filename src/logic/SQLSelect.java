package logic;

import java.util.List;
import armdb.ConnectHost;
import armdb.QueryResult;
import armdb.SQLQuery;
import armdb.SQLQueryException;

public class SQLSelect extends SQLQuery {
    
  public SQLSelect(ConnectHost con) {
    super(con);
  }
  
  public QueryResult result(String table, List<String> columns, String constraint) throws SQLQueryException {
    
    // Initiate selection query
    StringBuilder tempQuery = new StringBuilder("SELECT ");
    
    // Add all or specific columns to query
    if (columns.isEmpty()) {
      tempQuery.append("*");
    }
    else {
      for (String col : columns) {
        tempQuery.append(col + ",");
      }
      tempQuery.deleteCharAt(tempQuery.length() - 1);
    }
    
    // Add table to query
    tempQuery.append(" FROM " + table);
    
    // Add constraints to query
    tempQuery.append(" " + constraint);
    
    return this.statement(tempQuery.toString());
  }
    
}
