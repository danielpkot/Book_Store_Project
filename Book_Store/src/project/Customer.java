package project;

/*
 * Abstraction Function:
 * AF(c) = A Customer object c where:
 *         - c is a User with a username and password
 *         - c.points represents the loyalty points earned
 *         - c.state represents the customer's current status (e.g., Silver or Gold)
 *
 * Representation Invariant:
 * RI(c) = true if:
 *         - c.points ≥ 0
 *         - c.state ≠ null
 */
public class Customer extends User {
    //Overview: Customer Object representing a customer, with a username
    //          password, points and loyalty state
    private int points;
    private CustomerState state;
    
    /*
     * Requires: username and password are not null, points ≥ 0
     * Modifies: this
     * Effects: Initializes a customer and assigns Silver or Gold state based on points
     */
    public Customer(String username, String password, int points) {
        super(username, password);
        this.points = points;
        this.state = (points >= 1000) ? new GoldState() : new SilverState();
    }
    
    /*
     * Requires: none
     * Modifies: none
     * Effects: Returns the number of loyalty points the customer has
     */
    public int getPoints() { return points; }
    
    /*
     * Requires: points p >= 0
     * Modifies: Points
     * Effects: Sets the points of the Customer
     */
    public void setPoints(int p) { points = p; }
    
    /*
     * Requires: state =/= null
     * Modifies: none
     * Effects: Returns the number of loyalty points the customer has
     */
    public void setState(CustomerState state) { this.state = state; }

    /*
     * Requires: amount ≥ 0
     * Modifies: points (via state), possibly state (if points cross threshold)
     * Effects: Applies state-specific rules to update points after purchase
     */
    public void purchase(double amount) { state.purchase(this, amount); }
    
    public void purchaseWithPoints(double amount) { state.purchaseWithPoints(this, amount); }
    
    
    /* 
     * Requires: none
     * Modifies: none
     * Effects: Returns a string in the format: username,password,points
     */
    @Override
    public String toString() {
        return super.getUsername() + "," + super.getPassword() + "," + points;
    
    }
    
    
    
    
}
