package project;


public class Customer extends User {
    
    private int points;
    private CustomerState state;
    
    /**
     * Constructs a new Customer with the given username, password, and points.
     * 
     * Requires: username and password are not null, points ≥ 0
     * Modifies: this
     * Effects: Initializes a customer and assigns Silver or Gold state based on points
     */
    public Customer(String username, String password, int points) {
        super(username, password);
        this.points = points;
        this.state = (points >= 1000) ? new GoldState() : new SilverState();
    }
    
    /**
     * Returns the current point total of the customer.
     * 
     * Requires: none
     * Modifies: none
     * Effects: Returns the number of loyalty points the customer has
     */
    public int getPoints() { return points; }
    
    /**
     * Returns the current point total of the customer.
     * 
     * Requires: none
     * Modifies: Points
     * Effects: Sets the points of the Customer
     */
    public void setPoints(int p) { points = p; }
    
    /**
     * Returns the current point total of the customer.
     * 
     * Requires: none
     * Modifies: none
     * Effects: Returns the number of loyalty points the customer has
     */
    public void setState(CustomerState state) { this.state = state; }

    /**
     * Initiates a purchase which is handled by the current loyalty state.
     * 
     * Requires: amount ≥ 0
     * Modifies: points (via state), possibly state (if points cross threshold)
     * Effects: Applies state-specific rules to update points after purchase
     */
    public void purchase(double amount) { state.purchase(this, amount); }
    
    /**
     * Converts the customer data to a string for file storage.
     * 
     * Requires: none
     * Modifies: none
     * Effects: Returns a string in the format: username,password,points
     */
    @Override
    public String toString() {
        return super.getUsername() + "," + super.getPassword() + "," + points;
    
    }
    
    
    
    
}
