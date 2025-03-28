package project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;


public class CustomerManager {
   //Overview: Book manager used for adding/removing books from
    //          the list of books, also used for retrieving the list
    //           of books
    
    private final static String FILE_NAME = "customers.txt";
    
    private static CustomerManager instance = null;
    
    private ArrayList<Customer> customers;
    
    
    //Effects: Instantiates a CustomerManager object
    private CustomerManager(){}
    
    //Effects: Returns the single instance of the CustomerManager object, if
    //         no instance is instantiated it creates one.
    public static CustomerManager getInstance(){
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
         for (Customer c1 : customers) {
            if (c1.getUsername().equals(c.getUsername())) {
                System.out.println("Customer already exists!");
                return;
            }
        }
        customers.add(c);
    }
    
    //Modifies: this.customers
    //
    //Effects: Removes a customer from the customers arraylist
    public void Remove(Customer c){
        customers.remove(c);
    }
    
    public ArrayList<Customer> getCusomters(){
        return customers;
    }
    
    // Saving this for later gonna need to figure out how to parse
    public void loadCustomers(){
        
    }
    
    
    // Idk how to write specifications for this, since its modifying a file.
    public void saveCustomers(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Customer customer : customers) {
                writer.write(customer.toString() + "," + (customer.getPoints() >= 1000 ? "Gold" : "Silver"));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }
}
    
    
 

