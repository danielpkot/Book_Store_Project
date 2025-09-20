package project.FXML;

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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import project.Book;
import project.BookManager;
import project.Owner;
/*
 * Abstraction Function:
 * AF(bec) = A BookEditorController object where:
 *         - bec.owner represents the admin
 *         - bec.stage is the bookEditor gui
 *         - bec.bookEditorTable is the table containing all books
 *         - bec.bookName is the column with book names
 *         - bec.bookPrice is the column with book prices
 *         - bec.nameInput represents admins input into name textbox
 *         - bec.priceInput represents admins input into price textbox
 *
 * Representation Invariant:
 * RI(bec) = true if:
 *         - bec.owner != null
 *         - bec.stage,bec.scene,bec.root are valid javaFX objects
 */

public class BookEditorController implements Initializable {

    
    //Stage, Scene and Parent used for switching to a different GUI window
    private Stage stage;
    private Scene scene;
    private Parent root;
    //Owner object for managing books
    private Owner owner = Owner.getInstance();
    
    //GUI items
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
    
    
  /*
   *Requires: ownerpage.fxml is in the directory 
   *Modifies: this.stage & this.scene
   *Effects: Sets the stage to the admin stage
   */
   public void switchToAdminPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ownerPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
   /*
   *Requires: nameInput and priceInput are not null
   *Modifies:owner.bookManager.books,bookEditTable, NameInput, PriceInput 
   *Effects: if price is positive & a double, adds the specified book and update the 
   *         bookEditTable to display, else return string specifying either the price is
   *         not a double or not positive.
   */
   public void addBook(ActionEvent event) throws IOException{
       
       //Gathers info from the textfields and stores them
       String name = nameInput.getText();
       String price =  priceInput.getText();
       
       
       try{
           //checks that the second input is a double
           if ((double) Double.parseDouble(price) > 0){
            //creates a new Book item with  the inputs and adds it to the book list
            owner.addBook(new Book(name,(double) Double.parseDouble(price)));
            //Creates an Observable List(needed for table view), from the book list
            ObservableList<Book> bookList = FXCollections.observableList(owner.getBookManager().getBooks());
            //Places the data into the table, and clears text inputs.
             bookEditTable.setItems(bookList);
             nameInput.clear();
             priceInput.clear();
           }else{
               System.out.println("Price must be positive");
           }
           
       }catch(NumberFormatException e){
           System.out.println("Price not a double");
       }     
    }
   
   /*
   *Requires: bookEditTable != null
   *Modifies: bookEditTable, owner.bookManager.books
   *Effects: Removes the selected book from the table & booklist
   */
    public void removeBook(ActionEvent event) throws IOException{
        ObservableList<Book> selectedBooks , bookList;
        //gets items from table
        bookList = bookEditTable.getItems();
        
        //Gives the rows that were selected.
        selectedBooks = bookEditTable.getSelectionModel().getSelectedItems();
        
        //removes the selected books
        bookList.removeAll(selectedBooks);
    }
    
    /*
   *Requires: bookEditTable,bookName, bookPrice, owner.bookManger are not null
   *Modifies: bookName & bookPrice table Columns and bookEditTable
   *Effects: Initializes the table to display books
   */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //primes the columns with the specified getters, ex. Book Class getName()
        bookName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        //places the bookList into an observable list, uses it for the table, lets you multiselect.
        ObservableList<Book> bookList = FXCollections.observableList(owner.getBookManager().getBooks());
        bookEditTable.setItems(bookList);
        bookEditTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    
    
}
