import java.util.List;
import armdb.ConnectHost;
import armdb.QueryResult;
import armdb.SQLQuery;
import armdb.SQLQueryException;

public class SQLUpdate extends SQLQuery {
    
  public SQLUpdate(ConnectHost con) {
    super(con);
  }
  
  public QueryResult result(String table, List<String> columns, List<String> values, String constraint) throws SQLQueryException {
    
    // Initiate update query
    StringBuilder tempQuery = new StringBuilder("UPDATE " + table + " SET ");
    
    // Verify arguments are valid
    if (columns.size() != values.size()) {
      throw new SQLQueryException("Column count must match value count.");
    }
    
    // Add list of values to set
    for (int i = 0; i < columns.size(); i++) {
      tempQuery.append(columns.get(i) + " = " + values.get(i) + ",");
    }
    tempQuery.deleteCharAt(tempQuery.length() - 1);
    
    // Add constraints to query
    tempQuery.append(constraint);
    
    return this.statement(tempQuery.toString());
  }
  
}