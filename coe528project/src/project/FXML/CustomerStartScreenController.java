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
import project.Customer;
import project.Owner;


public class CustomerStartScreenController implements Initializable {
    
    //Owner for managing books
    private Owner owner = Owner.getInstance();
    //Customer to use Customer methods
    private Customer c;
    
    //Stage, Scene and Parent used for switching to a different GUI window
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    //Object for using GUI items
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
        //Go through the customer and find the correct Customer
        for(Customer customer: owner.getCustomerManager().getCustomers()){
            if (customer.getLogin()){
                System.out.println(customer.toString());
                c = customer;
            }
        }
        //Set the top text using customer information
        topText.setText("Welcome "+c.getUsername()+". You have "+ c.getPoints() +
                " points. Your status is " + ((c.getPoints() >= 1000) ? "Gold" : "Silver") );
        //primes the columns with the specified getters, ex. Customer Class getUsername()
        bookName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        select.setCellValueFactory(new PropertyValueFactory<Book, CheckBox>("select"));
        //places the CustomerList into an observable list, uses it for the table, lets you multiselect.
        ObservableList<Book> bookList = FXCollections.observableList(owner.getBookManager().getBooks());
        bookTable.setItems(bookList);
        bookTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    
      //Switches to Customer page
    public void switchToLoginPage(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        c.setLogin(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void buy(ActionEvent event) throws IOException{
        
        //flag for making sure books are selected, before attempting any logic
        boolean  flag = false;
        
        //find the selected books and place them into a list, add up the cost 
        // of the books
        double totalCost=0;
        ArrayList<Book> removed = new ArrayList<Book>();
        for(Book b: owner.getBookManager().getBooks()){
            if(b.getSelect().isSelected()){
                flag = true;
                totalCost += b.getPrice();
                
                removed.add(b);
            }
        }
        //purchase the books, and remove them, note if nothing was selected
        // nothing happens 
        c.purchase(totalCost);
        owner.getBookManager().getBooks().removeAll(removed);
        
        if (flag){
            // if something was selected then move to the cost screen
            FXMLLoader loader =  new FXMLLoader((getClass().getResource("customerCostScreen.fxml")));
            Parent root = loader.load();
            
            //getting the loader for the next file, to pass it information
            // such as cost 
            CustomerCostScreenController cc = loader.getController();
            cc.setText(totalCost,c);
            
            //set the stage and switch to it
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
            
    }
    
    
     public void buyWithPoints(ActionEvent event) throws IOException{
        
        //flag for making sure books are selected, before attempting any logic
        boolean  flag = false;
        //tOtal cost
        double totalCost = 0;
         //find the selected books and place them into a list, add up the cost 
        // of the books        
        ArrayList<Book> removed = new ArrayList<Book>();
        for(Book b: owner.getBookManager().getBooks()){
            if(b.getSelect().isSelected()){
                flag = true;
                totalCost += b.getPrice();
                removed.add(b);
            }
        }
      
       if (flag){
            //remove the selected books
            owner.getBookManager().getBooks().removeAll(removed);
            //getting the loader for the next file, to pass it information
            // such as cost 
            FXMLLoader loader =  new FXMLLoader((getClass().getResource("customerCostScreen.fxml")));
            Parent root = loader.load();
            
            //Calculate the display costs
            double displayCost;
            if ( (totalCost - (c.getPoints()/100)) <= 0){
                displayCost = 0;
            }
            else{
                displayCost = totalCost - (int)(c.getPoints()/100);
                
            }
            //purchase the books
            c.purchaseWithPoints(totalCost);
            
            
            CustomerCostScreenController cc = loader.getController();
            //pass info to next window
            cc.setText(displayCost,c);
            c.setLogin(false);
            //set the next Stage
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
            
    }
}

  