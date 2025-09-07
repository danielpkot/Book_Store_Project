package project.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.Book;
import project.Customer;
import project.Owner;


public class LoginPageController implements Initializable  {
    
   //Object representing the GUI items
   @FXML
   private TextField usernameField;
   
   @FXML
   private TextField passwordField;
   
   
   @FXML
   private Text warning;
 
   //Stage, Scene and Parent used for switching to a different GUI window
   private Stage stage;
   private Scene scene;
   private Parent root;
   
   @FXML
   private void handleLogin(ActionEvent event) throws IOException {
       
       
       Owner owner = Owner.getInstance();
       
       //takes the username and password and stores it
       String input1 = usernameField.getText();
       String input2 = passwordField.getText();
       
       boolean loggedin = false;
       
       if(input1.equals(owner.getUsername()) && input2.equals(owner.getPassword())){
           //if the stored inputs correspond to the owner, log into the owner
           // and loadd the owner page
           System.out.println("Logged in: Owner");
           loggedin = true;
           FXMLLoader loader = 
                   new FXMLLoader(getClass().getResource("ownerPage.fxml"));
           root = loader.load();
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       else{
           for(Customer c: owner.getCustomerManager().getCustomers()){
               if (c.getUsername().equals(input1) 
                       && c.getPassword().equals(input2)){
                   //go through the whole list, if the inputs correspond to a 
                   // customer, set the customers login status as true
                   // and proceed to the customer start screen
                   c.setLogin(true);
                   System.out.println("Logged In: " + c.toString());
                   loggedin = true;
                   FXMLLoader loader = 
                   new FXMLLoader(getClass().getResource("customerStartScreen.fxml"));
                   root = loader.load();
                   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                   scene = new Scene(root);
                   stage.setScene(scene);
                   stage.show();
               }
           }
       }
       
       if (loggedin == false){
           //pops up a text saying you were unsuccessfull if your
           // inputs don't correspond to any customer or owner
           warning.setText("Username and password do not match");
       }
   }  
   
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ensuring when you go to login page all customers are logged out
        for(Customer c: Owner.getInstance().getCustomerManager().getCustomers()){
            if (c.getLogin()==true){
            c.setLogin(false);
            }
        }
        //Ensuring all boxes are unchecked when someone relogs in
        for(Book b: Owner.getInstance().getBookManager().getBooks()){
            if(b.getSelect().isSelected()){
                CheckBox pass = new CheckBox();
                pass.setSelected(false);
                b.setSelect(pass);
            }
        }
    }  
   
}
