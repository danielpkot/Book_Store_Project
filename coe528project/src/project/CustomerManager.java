package project;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileReader;
/*
 * Abstraction Function:
 * AF(cm) = A CustomerManager object cm where:
 *          - cm.customers is a list of all Customer objects in the system
 *          - The file "customers.txt" is used to persist the customer data
 *
 * Representation Invariant:
 * RI(cm) = true if:
 *          - cm.customers ≠ null
 *          - For all integers i, j (0 ≤ i < j < cm.customers.size()),
 *            cm.customers.get(i).getUsername() ≠ cm.customers.get(j).getUsername()
 *          - FILE_NAME refers to a valid writable path
 */


public class CustomerManager {
   /** Overview: CustomerManager for managing the customers in the list
    * 
    */
    
    private final static String FILE_NAME = "customers.txt";
    private static CustomerManager instance = null;
    private ArrayList<Customer> customers;
    
    
    /*
     * Requires: none
     * Modifies: this
     * Effects: Initializes the customers list as empty
     */
    private CustomerManager(){
        customers = new ArrayList<Customer>();
    }
    
     /*
     * Requires: none
     * Modifies: instance if null
     * Effects: Returns singleton instance 
     */
    public static CustomerManager getInstance(){
        if (instance == null){
            instance = new CustomerManager();
        }
        return instance;
    }
    
    /**
     * Requires: none
     * Modifies: none
     * Effects: Returns a list of all customers
     */
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    
    
     /*
     * Modifies: this.customers
     * Effects: Adds the customer if valid username/password and 
     *          not a duplicate
     */
    public void addCustomer(Customer c){
        if (c == null){
            return;
        }
        for (Customer c1 : customers) {
            if (c1.getUsername().trim().equals(c.getUsername().trim())) {
                System.out.println("Customer already exists!");
                return;
            }
        }
        if(c.getUsername().isEmpty() || c.getPassword().isEmpty()){
            return;
        }
        customers.add(c);
        System.out.println("Customer added: " + c.getUsername());
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
        System.out.println("if Customer exists, Customer removed: " + c.getUsername());
    }
    
    
    
    // REQUIRES: FILE_NAME must be accessible
    // MODIFIES: this.customers
    // EFFECTS: Populates customers from file 
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
    
    
 

