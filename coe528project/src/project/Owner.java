
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
        System.out.println("Book added: " + b.getName() + " ($" + b.getPrice() + ")");
    }

    public void removeBook(Book b){
            bookManager.remove(b);
            System.out.println("Book removed: " + b.getName());
            return;
       
    }

    public void addCustomer(Customer c){
        customerManager.addCustomer(c);
        System.out.println("Customer added: " + c.getUsername());
    }

    public void removeCustomer(Customer c){     
        customerManager.remove(c);
        System.out.println("Customer removed: " + c.getUsername());
        return;

    }
    
    public BookManager getBookManager(){
        return bookManager;
    }

    public CustomerManager getCustomerManager(){
        return customerManager;
    }

}