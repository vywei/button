package logic;

import java.util.List;
import armdb.ConnectHost;
import armdb.QueryResult;
import armdb.SQLQuery;
import armdb.SQLQueryException;

public class SQLInsert extends SQLQuery {
    
  public SQLInsert(ConnectHost con) {
    super(con);
  }
  
  public QueryResult result(String table, List<String> columns, List<String> values) throws SQLQueryException {
    
    // Initiate insert query
    StringBuilder tempQuery = new StringBuilder("INSERT INTO " + table + "(");
    
    // Verify arguments are valid
    if (columns.size() != values.size()) {
      throw new SQLQueryException("Column count must match value count.");
    }
    
    // Add list of columns to query
    for (String col : columns) {
      tempQuery.append("`" + col + "`,");
    }
    tempQuery.deleteCharAt(tempQuery.length() - 1);
    
    tempQuery.append(") VALUES (");
    
    // Add list of values to query
    for (String val : values) {
      tempQuery.append("'" + val + "',");
    }
    tempQuery.deleteCharAt(tempQuery.length() - 1);
    
    tempQuery.append(")");
    
    return this.statement(tempQuery.toString());
  }
  
}