package project.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.Customer;
import project.CustomerManager;
import project.Owner;


public class LoginPageController implements Initializable {
    
   
   @FXML
   private TextField usernameField;
   
   @FXML
   private TextField passwordField;
    
   @FXML
   private Button login;
   
   private Stage stage;
   private Scene scene;
   private Parent root;
   
   @FXML
   private void handleLogin(ActionEvent event) throws IOException{
       
       Owner owner = Owner.getInstance();
       
       String input1 = usernameField.getText();
       String input2 = passwordField.getText();
       
       boolean loggedin = false;
       
       if(input1.equals(owner.getUsername()) && input2.equals(owner.getPassword())){
           System.out.println("Logged in: Owner");
           loggedin = true;
           FXMLLoader loader = 
                   new FXMLLoader(getClass().getResource("adminPage.fxml"));
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
                   System.out.println("Sucess");
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
       
       if (loggedin = false){
           System.out.println("Username and password do not match");
       }
   }
  
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
   
}
