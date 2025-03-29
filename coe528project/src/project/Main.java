
package project;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         BookManager manager = BookManager.getInstance();
        manager.loadBooks();

        System.out.println("Loaded Books:");
        for (Book book : manager.getBooks()) {
            System.out.println("Name: " + book.getName() + " | Price: " + book.getPrice());
        }
        launch(args);
    }
    
}
