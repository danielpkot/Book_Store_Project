
package project;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class BookEditorController implements Initializable {

    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    BookManager bm = BookManager.getInstance();
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Book> bookEditTable;

    @FXML
    private TableColumn<Book, String> bookName;

    @FXML
    private TableColumn<Book, Double> bookPrice;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField priceInput;
    
    
   public void switchToAdminPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
   public void addBook(ActionEvent event) throws IOException{
       
       String name = nameInput.getText();
       String price =  priceInput.getText();
       
       try{
           bm.addBook(new Book(name,(double) Double.parseDouble(price)));
           
       }catch(NumberFormatException e){
           System.out.println("Price not a double");
       }
       
       ObservableList<Book> bookList = FXCollections.observableList(bm.getBooks());
       bookEditTable.setItems(bookList);
       
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bm.loadBooks();
        ObservableList<Book> bookList = FXCollections.observableList(bm.getBooks());
        bookEditTable.setItems(bookList);
    }    
    
}
