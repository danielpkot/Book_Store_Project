package project;

/**
 * Represents the Gold loyalty state for a customer.
 *
 * Abstraction Function:
 * - Customers in the Gold state earn more rewards per purchase.
 * - This class defines how points are earned when in Gold tier.
 *
 * Representation Invariant:
 * - None specific; behavior is determined by the logic in purchase method.
 */
public class GoldState implements CustomerState {
    
    /**
     * Applies the Gold state reward rules to the customer's purchase.
     *
     * Requires: customer is not null, amount ≥ 0
     * Modifies: customer's points
     * Effects: Adds 10 points per dollar spent to the customer
     */
    @Override
     public void purchase(Customer customer, double amount) {
        customer.setPoints( customer.getPoints()+(int) (amount * 10));
    }
    
    
}
