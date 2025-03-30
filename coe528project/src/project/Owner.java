
package project;

import java.util.ArrayList;

/**
 *
 
@author roney*/
public class Owner extends User {
    private BookManager bookManager = BookManager.getInstance();
    private CustomerManager customerManager = CustomerManager.getInstance();
    private static Owner instance = null;

    private Owner(){
        super("admin", "admin");
       
    }

    public static Owner getInstance(){
        if( instance == null){
            instance = new Owner();
        }
        return instance;
    }

    public void addBook(Book b){
        bookManager.addBook(b);
    }

    public void removeBook(Book b){
            bookManager.remove(b);
            return;
       
    }

    public void addCustomer(Customer c){
        customerManager.addCustomer(c);
    }

    public void removeCustomer(Customer c){     
        customerManager.remove(c);
        return;

    }
    
    public BookManager getBookManager(){
        return bookManager;
    }

    public CustomerManager getCustomerManager(){
        return customerManager;
    }

}