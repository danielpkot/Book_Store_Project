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

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Owner owner = Owner.getInstance();
    
   
    
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
    
    
    public void addBook(ActionEvent event) throws IOException{
       
       String name = nameInput.getText();
       String password =  passwordInput.getText();
       
        owner.addCustomer(new Customer(name,password, 0));
        ObservableList<Customer> customerList = FXCollections.observableList(owner.getCustomerManager().getCustomers());
        customerEditTable.setItems(customerList);
        nameInput.clear();
        passwordInput.clear();
    }
    
    
    public void removeBook(ActionEvent event) throws IOException{
        ObservableList<Customer> selectedCustomers , customerList;
        customerList = customerEditTable.getItems();
        
        //Gives the rows that were selected.
        selectedCustomers = customerEditTable.getSelectionModel().getSelectedItems();
        // Removes them
        customerList.removeAll(selectedCustomers);
    }
    
   public void switchToAdminPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("Username"));
        customerPassword.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
        customerPoints.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("points"));
        ObservableList<Customer> customerList = FXCollections.observableList(owner.getCustomerManager().getCustomers());
        customerEditTable.setItems(customerList);
        customerEditTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    
    
}
