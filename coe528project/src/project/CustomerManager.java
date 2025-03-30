package project;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileReader;


public class CustomerManager {
   /**
    * Manages all customer records in the system.
    *
    * Abstraction Function:
    * - CustomerManager stores, adds, removes, and persists customer data.
    * - Acts as a centralized registry for all customer-related operations.
    *
    * Representation Invariant:
     * - customers list must not be null
    * - FILE_NAME must be a valid file path
    * - No duplicate usernames allowed in the customers list
    */
    
    private final static String FILE_NAME = "customers.txt";
    private static CustomerManager instance = null;
    private ArrayList<Customer> customers;
    
    
    /**
     * Constructs a CustomerManager with an empty customer list.
     *
     * Requires: none
     * Modifies: this
     * Effects: Initializes the customers list as empty
     */
    private CustomerManager(){
        customers = new ArrayList<Customer>();
    }
    
     /**
     * Retrieves the singleton instance of CustomerManager.
     *
     * Requires: none
     * Modifies: instance if null
     * Effects: Returns the same instance on every call
     */
    public static CustomerManager getInstance(){
        if (instance == null){
            instance = new CustomerManager();
        }
        return instance;
    }
    
    /**
     * Gets all ArrayList of customers managed by the system.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns a list of all customers
     */
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    
    
     /**
     * Adds a new customer to the system if the username is unique.
     *
     * Modifies: this.customers
     * Effects: Adds the customer if no duplicate username exists
     */
    public void addCustomer(Customer c){
        if (c == null){
            return;
        }
        for (Customer c1 : customers) {
            if (c1.getUsername().equals(c.getUsername())) {
                System.out.println("Customer already exists!");
                return;
            }
        }
        if(c.getUsername().isEmpty() || c.getPassword().isEmpty()){
            return;
        }
        customers.add(c);
    }
    
    /**
     * Removes a customer from the system.
     *
     * Modifies: this.customers
     * Effects: Deletes the specified customer from the list
     */
    public void remove(Customer c){
        if (c == null){
            return;
        }
        customers.remove(c);
    }
    
    
    
    
    // Saving this for later gonna need to figure out how to parse
    public void loadCustomers(){
        try( BufferedReader reader = new BufferedReader(new FileReader("customers.txt"))){
         String line;
         while((line = reader.readLine()) != null){
             String[] parameters = line.split(",");
             this.addCustomer(new Customer(parameters[0],parameters[1],Integer.parseInt(parameters[2])));
         }
             
        }catch(IOException e){System.out.println("Do smtng");}
    }
  
    /**
     * Saves current customer list to file.
     *
     * Requires: FILE_NAME is writable
     * Modifies: customers.txt
     * Effects: Overwrites the file with current customer records
     */
    public void saveCustomers(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }
}
    
    
 

