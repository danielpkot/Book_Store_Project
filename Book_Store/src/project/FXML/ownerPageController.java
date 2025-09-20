package project.FXML;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/*
 * Abstraction Function:
 * AF(opc) = an ownerPage Controller object where:
 *         - opc.stage is the ownerPageGui gui
 *
 * Representation Invariant:
 * RI(opc) = true if:
 *         - opc.stage,opc.scene,opc.root are valid javaFX objects
 */

public class ownerPageController  {
    //Stage, Scene and Parent used for switching to a different GUI window
    private Stage stage;
    private Scene scene;
    private Parent root;
    
   /*
   *Requires: loginPage.fxml is in the directory 
   *Modifies: this.stage & this.scene
   *Effects: Sets the stage to the login page
   */
    public void switchToLoginPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     /*
    *Requires: bookEditor.fxml is in the directory 
    *Modifies: this.stage & this.scene
    *Effects: Sets the stage to the bookEditior stage
    */
    public void switchToBookEditorPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("bookEditor.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     /*
      *Requires: customerEditor.fxml is in the directory 
      *Modifies: this.stage & this.scene
      *Effects: Sets the stage to the Customer Editor page
      */
    public void switchToCustomerEditorPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("customerEditor.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
   
    
}
