package project;


public class SilverState implements CustomerState {
   // gotta do this
    
    @Override
    public void purchase(Customer customer, double amount) {
        customer.addPoints((int) (amount * 10));
        if (customer.getPoints() >= 1000) {
            customer.setState(new GoldState());
        }
    }
    
}
