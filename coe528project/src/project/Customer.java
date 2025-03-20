package project;


public class Customer extends User {
    
    private int points;
    
    private CustomerState state; 
    
    //gotta do this
    public Customer(String name, String password){
        super(name,password);
    }
    
    //Effects: Returns Customer points
    public int getPoints(){
        return points;
    }
    
    
    
    
}
