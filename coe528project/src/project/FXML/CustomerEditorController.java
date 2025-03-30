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

/**
 * FXML Controller class
 *
 * @author danie
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
    
    //Method for adding Customers
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
    
    //Method for removing customers
    public void removeCustomer(ActionEvent event) throws IOException{
        ObservableList<Customer> selectedCustomers , customerList;
        //gets items from table
        customerList = customerEditTable.getItems();
        
        //Gives the rows that were selected.
        selectedCustomers = customerEditTable.getSelectionModel().getSelectedItems();
        // Removes them
        customerList.removeAll(selectedCustomers);
    }
   
   //Switches to main owner page
   public void switchToAdminPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ownerPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
   
    @Override
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
