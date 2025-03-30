package project;

/**
 * Represents the Silver loyalty state for a customer.
 *
 * Abstraction Function:
 * - Customers in the Silver state earn points and can upgrade to Gold status.
 *
 * Representation Invariant:
 * - None specific; behavior is defined in the purchase method.
 */
public class SilverState implements CustomerState {
   // gotta do this
     /**
     * Applies the Silver state reward rules to the customer's purchase.
     *
     * Requires: customer is not null, amount ≥ 0
     * Modifies: customer's points, possibly customer's state
     * Effects: Adds 10 points per dollar; upgrades to Gold if points ≥ 1000
     */
    @Override
    public void purchase(Customer customer, double amount) {
        customer.setPoints( customer.getPoints() +((int)amount)* 10);
        if (customer.getPoints() >= 1000) {
            customer.setState(new GoldState());
        }
    }
    
    @Override
    public void purchaseWithPoints(Customer customer, double amount){
        if (customer.getPoints()/100 >= amount){
            customer.setPoints(customer.getPoints()-((int) amount)* 100);
        }
        else{
            amount -= customer.getPoints()/100;
            customer.setPoints((int)amount*10);
        }
        if (customer.getPoints() >= 1000) {
            customer.setState(new GoldState());
        }

    }
    
}
