package project;
/*
 * Abstraction Function:
 * AF(s) = A CustomerState object s where:
 *         - s defines the behavior for how a customer earns or uses points
 *           when making a purchase depending on their loyalty tier
 *
 * Representation Invariant:
 * RI(s) = true if:
 *         - s correctly implements the point handling logic for a specific state
 *         - s.purchase(customer, amount) handles valid non-null customer and amount ≥ 0
 */
public interface CustomerState {
    
    // REQUIRES: customer ≠ null, amount ≥ 0
    // MODIFIES: customer's points, possibly customer's state
    // EFFECTS: Applies state-specific logic to process a purchase
    void purchase(Customer customer, double amount);
    
    // REQUIRES: customer ≠ null, amount ≥ 0
    // MODIFIES: customer's points, possibly customer's state
    // EFFECTS: Applies state-specific logic to process a purchase
    void purchaseWithPoints(Customer customer, double amount);
    
    
}
