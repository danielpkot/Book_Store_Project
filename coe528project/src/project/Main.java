
package project;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    BookManager bm = BookManager.getInstance();
    CustomerManager cm = CustomerManager.getInstance();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        
        bm.loadBooks();
        cm.loadCustomers();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnCloseRequest(Event -> {saveData();});
        stage.show();
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void saveData(){
        System.out.println("Exiting");
        bm.saveBooks();
        cm.saveCustomers();
        
    }
    
}
