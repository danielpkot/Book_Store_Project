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
/*
 * Abstraction Function:
 * AF(css) = A CustomerStarScreenController object where:
 *         - ccs.stage is the customer start screen gui
 *         - ccs.owner represents the admin
 *         - ccs.c represents the customer
 *         - ccs.bookTable is the table containing all books
 *         - ccs.bookName is the column with book names
 *         - ccs.bookPrice is the column with book pricesput into password textbox
 *         - ccs.select is marker for selected books
 *         - ccs.topText represents the welcome text at the top of the screen
 *
 * Representation Invariant:
 * RI(css) = true if:
 *         - css.owner != null
 *         - css.c != null after initializing
 *         - css.stage,css.scene,css.root are valid javaFX objects
 */  

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
    
   /* 
   *Modifies: c, topText, bookName, bookPrice, select, bookTable
   *Effects: Sets the stage to the admin page
   */ 
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
    
   /*
   *Requires: loginPage.fxml is in the directory 
   *Modifies: this.stage & this.scene
   *Effects: Sets the stage to the admin stage
   */
    public void switchToLoginPage(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        c.setLogin(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
   /*
   *Requires: customerCostScreen.fxml is in the directory 
   *Modifies: c.points, owner.bookManager.books,stage, scene
   *Effects: calculates cost of selected books, removes purchased
   *         books from store and if atleast one book was selected
   *         switch page to the cost screen
   */
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
    
    /*
   *Requires: customerCostScreen.fxml is in the directory 
   *Modifies: c.points, owner.bookManager.books,stage, scene
   *Effects: calculates cost of selected books & deduct discount 
   *         using points, removes purchased  books from table and if atleast
   *          one book was selected switch page to the cost screen
   */
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

  