package project;


public abstract class User {
    //Overview: User class, represents a user of the program with
    //          a username and password
    
    private String username;
    private String password;
    private boolean login = false;
    
     /**
     * Constructs a User with a given username and password.
     *
     * Requires: name and pass are not null
     * Modifies: this
     * Effects: Initializes the user with the given credentials
     */
    public User(String name, String pass){
        username = name;
        password = pass;
    }
    
    /**
     * Returns the login status of this user.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the stored login in status
     */
    public boolean getLogin(){
        return login;
    }
    
    public void setLogin(boolean cond){
        login = cond;
    }
    
    /**
     * Returns the username of this user.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the stored username
     */
    public String getUsername(){
        return username;
    }
    
    /**
     * Returns the password of this user.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the stored password
     */
    public String getPassword(){
        return password;
    }
}
