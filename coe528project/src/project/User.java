package project;


public abstract class User {
    //Overview: User class, represents a user of the program with
    //          a username and password
    
    private String username;
    private String password;
    
    
    public User(String name, String pass){
        username = name;
        password = pass;
    }
    
    // gotta figure ts out
    public boolean login(){
        return true;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
}
