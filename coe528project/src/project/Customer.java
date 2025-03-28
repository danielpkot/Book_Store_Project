package project;


public class Customer extends User {
    
    private int points;
    private CustomerState state;

    public Customer(String username, String password, int points) {
        super(username, password);
        this.points = points;
        this.state = (points >= 1000) ? new GoldState() : new SilverState();
    }

    public int getPoints() { return points; }
    public void addPoints(int p) { points += p; }
    public void setState(CustomerState state) { this.state = state; }

    public void purchase(double amount) { state.purchase(this, amount); }
    
    @Override
    public String toString() {
        return super.getUsername() + "," + super.getPassword() + "," + points;
    
    }
    
    
    
    
}
