
package project;

import java.util.ArrayList;

/*
 * Abstraction Function:
 * AF(o) = An owner object m where:
 *         - m.BookManager is the bookManager object
 *         - m.customerkManager is the customer object
 *         - m.instance is the singleton instance of owner
 *
 * Representation Invariant:
 * RI(m) = true if:
 *         - m.BookManager not null
 *         - m.customerkManager not null
 *@author roney*/


public class Owner extends User {
    private BookManager bookManager = BookManager.getInstance();
    private CustomerManager customerManager = CustomerManager.getInstance();
    private static Owner instance = null;

    private Owner(){
        super("admin", "admin");
       
    }
    
     /*
     * Requires: none
     * Modifies: instance if null
     * Effects: Returns the singleton instance of the owner
     */
    public static Owner getInstance(){
        if( instance == null){
            instance = new Owner();
        }
        return instance;
    }
    
     /*
     * Requires: none
     * Modifies: bookManager.books
     * Effects: adds a book to the book collection
     */
    public void addBook(Book b){
        bookManager.addBook(b);
    }
    
    /*
     * Requires: none
     * Modifies: bookManager.books
     * Effects: removes a book to the book collection
     */
    public void removeBook(Book b){
            bookManager.remove(b);
            return;
       
    }
    
     /*
     * Requires: none
     * Modifies: customerManager.customers
     * Effects: adds a customer to the customer list
     */
    public void addCustomer(Customer c){
        customerManager.addCustomer(c);
    }
    
      /*
     * Requires: none
     * Modifies: customerManager.customers
     * Effects: removes a customer to the customer list
     */
    public void removeCustomer(Customer c){     
        customerManager.remove(c);
        

    }
     /*
     * Requires: none
     * Modifies: none
     * Effects: returns bookManager object
     */
    public BookManager getBookManager(){
        return bookManager;
    }
    
    
     /*
     * Requires: none
     * Modifies: none
     * Effects: returns customerManager object
     */
    public CustomerManager getCustomerManager(){
        return customerManager;
    }

}