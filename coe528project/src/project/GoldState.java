package project;


public class GoldState implements CustomerState {
    //gotta implement this
    
    @Override
     public void purchase(Customer customer, double amount) {
        customer.addPoints((int) (amount * 10));
    }
    
    
}
