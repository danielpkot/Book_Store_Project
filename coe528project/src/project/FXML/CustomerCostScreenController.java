
package project.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.BookManager;
import project.Customer;
import project.CustomerManager;


public class CustomerCostScreenController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private BookManager bm = BookManager.getInstance();
    private CustomerManager cm = CustomerManager.getInstance();
    
    
    @FXML
    private Button logout;

    @FXML
    private Text pointsText;

    @FXML
    private Text totalCostText;
            
    public void setText(double totalCost,Customer c){
        totalCostText.setText("Total Cost: " + totalCost );
        pointsText.setText("Points: "+c.getPoints() + ", " + 
                ( (c.getPoints() >= 1000) ? "Gold" : "Silver"));
 
    }
    
     public void switchToLoginPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        for(Customer c: cm.getCustomers()){
            if (c.getLogin() == true){
                c.setLogin(false);
            }
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
