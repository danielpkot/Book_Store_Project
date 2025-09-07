package project.FXML;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;



public class OwnerPageController {
    //Stage, Scene and Parent used for switching to a different GUI window
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    //Go back to login page
    public void switchToLoginPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    //Go to book editor
    public void switchToBookEditorPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("bookEditor.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    //Go to customer editor
    public void switchToCustomerEditorPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("customerEditor.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
   
    
}
