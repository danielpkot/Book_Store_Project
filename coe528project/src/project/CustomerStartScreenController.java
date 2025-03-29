
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CustomerStartScreenController implements Initializable {
    
    BookManager bm = BookManager.getInstance();
    CustomerManager cm = CustomerManager.getInstance();
    Customer c;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Text topText;
    
    @FXML
    private TableView<Book> bookEditTable;

    @FXML
    private TableColumn<Book, String> bookName;

    @FXML
    private TableColumn<Book, Double> bookPrice;
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Customer customer: cm.getCustomers()){
            if (customer.getLogin()){
                System.out.println(customer.toString());
                c = customer;
            }
        }
        topText.setText("Welcome "+c.getUsername()+". You have "+ c.getPoints() +
                " points. Your status is " + ((c.getPoints() >= 1000) ? "Gold" : "Silver") );
        bookName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        ObservableList<Book> bookList = FXCollections.observableList(bm.getBooks());
        bookEditTable.setItems(bookList);
        bookEditTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void switchToLoginPage(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
    
}
