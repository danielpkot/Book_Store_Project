
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
import project.Owner;
/*
 * Abstraction Function:
 * AF(ccs) = A CustomerCostScreenController object where:
 *         - ccs.stage is the customerCostScreen gui
 *         - ccs.owner represents the admin
 *         - ccs.pointsText represents the points
 *         - ccs.totalCostText represents the total cost
 *
 * Representation Invariant:
 * RI(ccs) = true if:
 *         - ccs.owner != null
 *         - ccs.stage,ccs.scene,ccs.root are valid javaFX objects
 */

public class CustomerCostScreenController  {

    private Stage stage;
    private Scene scene;
    private Parent root;
    // Owner object for controlling the owner
    private Owner owner = Owner.getInstance();
    
    
    //Objects for controlling GUI items
    @FXML
    private Text pointsText;

    @FXML
    private Text totalCostText;
    
   /*
   *Requires: c and totalCost not null
   *Modifies: totalCostText, pointsText
   *Effects: Sets text at top of screen to display cost of books
   *         in cart and customers membership status
   */
    public void setText(double totalCost,Customer c){
        totalCostText.setText("Total Cost: " + totalCost );
        pointsText.setText("Points: "+c.getPoints() + ", " + 
                ( (c.getPoints() >= 1000) ? "Gold" : "Silver"));
 
    }
    
    /*
   *Requires: loginPage.fxml is in the directory 
   *Modifies: this.stage & this.scene
   *Effects: Sets the stage to the login screen
   */
    public void switchToLoginPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();
        
    }   
}
