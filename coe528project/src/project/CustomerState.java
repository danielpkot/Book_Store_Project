package project;

public interface CustomerState {
    
    void purchase(Customer customer, double amount);
            
    void purchaseWithPoints(Customer customer, double amount);
    
    
}
