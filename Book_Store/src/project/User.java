package project;

/*
 * Abstraction Function:
 * AF(u) = A User object u where:
 *         - u.username represents the login identifier for the user
 *         - u.password is the authentication credential
 *
 * Representation Invariant:
 * RI(u) = true if:
 *         - u.username ≠ null
 *         - u.password ≠ null
 */
public abstract class User {
    //Overview: User class, represents a user of the program with
    //          a username and password
    
    private String username;
    private String password;
    private boolean login = false;
    
     /*
     * Requires: name and pass are not null
     * Modifies: this
     * Effects: Initializes the user with the given credentials
     */
    public User(String name, String pass){
        username = name;
        password = pass;
    }
    
    /*
     * Requires: none
     * Modifies: none
     * Effects: Returns the stored login in status
     */
    public boolean getLogin(){
        return login;
    }
    
    /*
     * Requires: none
     * Modifies: none
     * Effects: sets the login status of the user
     */
    public void setLogin(boolean cond){
        login = cond;
    }
    
    /*
     * Requires: none
     * Modifies: none
     * Effects: Returns the stored username
     */
    public String getUsername(){
        return username;
    }
    
    /*
     * Requires: none
     * Modifies: none
     * Effects: Returns the stored password
     */
    public String getPassword(){
        return password;
    }
}
