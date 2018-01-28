import armdb.ConnectHost;             //import to make connection
import armdb.SQLQuery;                //import to make SQL query
import armdb.QueryResult;             //import to store the query results
import armdb.SQLQueryException;       //import to handle the exception thrown during query 

public class Database {
  
  //temporary name for proof of concept
  public static void main(String[] args) {

    String fileURL="";  //URL of 'handleSQL.php' file
    String host="localhost";                              //server host name
    String user="";                                           //user name
    String pass="";                                       //password
    String dbName="";                                       //database name

    ConnectHost con = new ConnectHost(fileURL, host, user, pass, dbName);  //make connection        
    SQLQuery query = new SQLQuery(con);         //SQLQuery object to execute select statement
    QueryResult qr;                           //QueryResult object to store the queried result
    try{
        qr=query.statement("select * from ");     //execution of query statement
            
        //qr holds the selected rows, let us print the values of some columns 
        //(say column_1 and column_2) of all rows
        while(qr.nextFlag()){                               //setting flag to next row till next row exists
            //print column_1 & column_2 value of row where flag is set

            System.out.print(qr.getValue("")+", ");
            System.out.print(qr.getValue(""));
        }
    }catch(SQLQueryException e){                            //catch exception if occurred
        System.out.println(e.getMessage());                 //print exception message
    }
}
}
