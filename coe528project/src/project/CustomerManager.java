package project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class CustomerManager {
   //Overview: Book manager used for adding/removing books from
    //          the list of books, also used for retrieving the list
    //           of books
    
    private final static String filename = "customers.txt";
    
    private static CustomerManager instance = null;
    
    private ArrayList<Customer> customers;
    
    
    //Effects: Instantiates a CustomerManager object
    private CustomerManager(){}
    
    //Effects: Returns the single instance of the CustomerManager object, if
    //         no instance is instantiated it creates one.
    public CustomerManager getInstance(){
        if (instance == null){
            instance = new CustomerManager();
        }
        return instance;
    }
    
    
    //Effects: Returns arraylist containing all customers
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    
    
    //Modifies: this.Customers
    //
    //Effects: Adds a Customer to the books arraylist
    public void addCustomer(Customer c){
        customers.add(c);
    }
    
    //Modifies: this.customers
    //
    //Effects: Removes a customer from the customers arraylist
    public void Remove(Customer c){
        customers.remove(c);
    }
    
    // Saving this for later gonna need to figure out how to parse
    public void loadCustomers(){
        
    }
    
    // Idk how to write specifications for this, since its modifying a file.
    public void saveCustomers(){
        try {
            FileWriter writer = new FileWriter(filename,true);
            for(Customer c : customers){
                writer.write(c.getUsername() + " " + c.getPassword() 
                        + " " + c.getPoints() +"\n" );
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    
} 

