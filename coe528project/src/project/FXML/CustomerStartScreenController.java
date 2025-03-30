package project.FXML;

import project.FXML.CustomerCostScreenController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.Book;
import project.BookManager;
import project.Customer;
import project.CustomerManager;


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
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> bookName;

    @FXML
    private TableColumn<Book, Double> bookPrice;
    
    @FXML
    private TableColumn<Book, CheckBox> select;
    
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
        select.setCellValueFactory(new PropertyValueFactory<Book, CheckBox>("select"));
        ObservableList<Book> bookList = FXCollections.observableList(bm.getBooks());
        bookTable.setItems(bookList);
        bookTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void switchToLoginPage(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        c.setLogin(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void buy(ActionEvent event) throws IOException{
        
     
        boolean  flag = false;
        
        double totalCost=0;
        ArrayList<Book> removed = new ArrayList<Book>();
        for(Book b: bm.getBooks()){
            if(b.getSelect().isSelected()){
                flag = true;
                totalCost += b.getPrice();
                
                removed.add(b);
            }
        }
        c.purchase(totalCost);
        bm.getBooks().removeAll(removed);
        
        if (flag){
            FXMLLoader loader =  new FXMLLoader((getClass().getResource("customerCostScreen.fxml")));
            Parent root = loader.load();
            
            CustomerCostScreenController cc = loader.getController();
            cc.setText(totalCost,c);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
            
    }
    
    
     public void buyWithPoints(ActionEvent event) throws IOException{
        
     
        boolean  flag = false;
        
        double totalCost = 0;
                
        ArrayList<Book> removed = new ArrayList<Book>();
        for(Book b: bm.getBooks()){
            if(b.getSelect().isSelected()){
                flag = true;
                totalCost += b.getPrice();
                removed.add(b);
            }
        }
        
       
        bm.getBooks().removeAll(removed);
        
      
       
       
        if (flag){
            FXMLLoader loader =  new FXMLLoader((getClass().getResource("customerCostScreen.fxml")));
            Parent root = loader.load();
            if ( (totalCost - c.getPoints()/100) <= 0){
            totalCost = 0;
            }
            else{
                totalCost = totalCost - c.getPoints()/100;
            }
            c.purchaseWithPoints(totalCost);
            CustomerCostScreenController cc = loader.getController();
            cc.setText(totalCost,c);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
            
    }
}

  