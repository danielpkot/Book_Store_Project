package project;

/*
 * Abstraction Function:
 * AF(ss) = A SilverState object ss where:
 *          - Customers in this state earn regular points and may upgrade to Gold
 *          - ss defines the behavior for point accumulation and upgrade condition
 *
 * Representation Invariant:
 * RI(ss) = true (no specific invariant constraints)
 */

public class SilverState implements CustomerState {
     /*
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
    
    
    // REQUIRES: customer ≠ null, amount ≥ 0
    // MODIFIES: customer's points, state(if threshold hit)
    // EFFECTS: remove 1 off the total cost per 100 points that the 
    //          customer has, if the customer doesn't have enough, treat
    //          it as if the customer buys with cash accumulating 10 points
    //          per dollar spent
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
