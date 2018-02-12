package logic;

public class User {

    private String name;
    private String password;
  
    
    public User(String name, String password){
        this.name = name;
        this.password = password; 
    }
    
    public String getName(){
        return name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String pass)
    {
        password = pass;
    }
    
}
