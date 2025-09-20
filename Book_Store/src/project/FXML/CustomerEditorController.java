/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import project.Customer;
import project.CustomerManager;
import project.Owner;

/*
 * Abstraction Function:
 * AF(cec) = A CustomerEditorController object where:
 *         - cec.stage is the customer Editor gui
 *         - cec.owner represents the admin
 *         - cec.customerEditorTable is the table containing all customers
 *         - cec.customerName is the column with customer names
 *         - cec.customerPassword is the column with customer passwords
 *         - cec.customerPoints is the column with customer points
 *         - cec.nameInput represents admins input into name textbox
 *         - cec.passwordInput represents admins input into password textbox
 *
 * Representation Invariant:
 * RI(cec) = true if:
 *         - cec.owner != null
 *         - cec.stage,cec.scene,cec.root are valid javaFX objects
 */
public class CustomerEditorController implements Initializable {
    //Stage, Scene and Parent used for switching to a different GUI window
    private Stage stage;
    private Scene scene;
    private Parent root;
    //Owner object for managing Customers
    private Owner owner = Owner.getInstance();
    
   
    //GUI items
    @FXML
    private TableView<Customer> customerEditTable;

    @FXML
    private TableColumn<Customer, String> customerName;

    @FXML
    private TableColumn<Customer, String> customerPassword;
    
    @FXML
    private TableColumn<Customer, Integer> customerPoints;


    @FXML
    private TextField nameInput;

    @FXML
    private TextField passwordInput;
    
   /*
   *Modifies: Owner.CustomerManager.customers, customerEditTable,
   *           nameInput, passwordInput
   *Effects: creates new customer object with given name and password,
   *          updates the customer table and clears text fields
   */
    public void addCustomer(ActionEvent event) throws IOException{
       //Gathers info from the textfields and stores them
       String name = nameInput.getText();
       String password =  passwordInput.getText();
       //creates a new Customer item with the inputs and adds it to the book list
        owner.addCustomer(new Customer(name,password, 0));
         //Creates an Observable List(needed for table view), from the customer list
        ObservableList<Customer> customerList = FXCollections.observableList(owner.getCustomerManager().getCustomers());
        //Places the data into the table, and clears text inputs.
        customerEditTable.setItems(customerList);
        nameInput.clear();
        passwordInput.clear();
    }
    
    /*
   *Modifies: customerEditTable
   *Effects: Removes Selected Customer from the customer table
   */
    public void removeCustomer(ActionEvent event) throws IOException{
        ObservableList<Customer> selectedCustomers , customerList;
        //gets items from table
        customerList = customerEditTable.getItems();
        
        //Gives the rows that were selected.
        selectedCustomers = customerEditTable.getSelectionModel().getSelectedItems();
        // Removes them
        customerList.removeAll(selectedCustomers);
    }
   
   /*
   *Requires: ownerPage.fxml is in the directory 
   *Modifies: this.stage & this.scene
   *Effects: Sets the stage to the admin page
   */
   public void switchToAdminPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ownerPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
   
    /*
   *Requires: ownerPage.fxml is in the directory 
   *Modifies: customerName,customerPassword,customerPoints
   *          columns, and customerEditTable
   *Effects:  Initializes customerEditTable with customers in 
   *          owner.customerManager.customers
   */
    public void initialize(URL url, ResourceBundle rb) {
        //primes the columns with the specified getters, ex. Customer Class getUsername()
        customerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("Username"));
        customerPassword.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
        customerPoints.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("points"));
        //places the CustomerList into an observable list, uses it for the table, lets you multiselect.
        ObservableList<Customer> customerList = FXCollections.observableList(owner.getCustomerManager().getCustomers());
        customerEditTable.setItems(customerList);
        customerEditTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    
    
}
