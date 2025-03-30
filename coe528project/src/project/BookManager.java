package project;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.*;

public class BookManager {
    
    
    
    private final static String FILE_NAME = "books.txt";
    private static BookManager instance = null;
    private ArrayList<Book> books;
    
     /**
     * Constructs a new BookManager and initializes an empty book list.
     * 
     * Requires: none
     * Modifies: this
     * Effects: Initializes the books list to empty
     */
    private BookManager(){
        books = new ArrayList<Book>();
    }
    
     /**
     * Returns the singleton instance of the BookManager.
     * 
     * Requires: none
     * Modifies: instance if null
     * Effects: Returns the existing BookManager instance or creates one
     */
    public static BookManager getInstance(){
        if (instance == null){
            instance = new BookManager();
        }
        return instance;
    }
    
    
     /**
     * Gets all the books currently in the manager.
     * 
     * Requires: none
     * Modifies: none
     * Effects: Returns the list of stored books
     */
    public ArrayList<Book> getBooks(){
        return books;
    }
    
    
     /**
     * Adds a book to the manager if it does not already exist.
     * 
     * Requires: b is not null
     * Modifies: this.books
     * Effects: Adds the book to the list if not already present
     */

    public void addBook(Book b){
        for (Book b1 : books) {
            if (b.getName().equals(b1.getName())) {
                System.out.println("Book already exists!");
                return;
            }
        }
        books.add(b);
    }
    
    /**
     * Removes a book from the manager.
     * 
     * Requires: b is not null and exists in the list
     * Modifies: this.books
     * Effects: Removes the specified book from the list
     */

    public void remove(Book b){
        books.remove(b);
    }
    
    
   /**
     * Saves all books to a file for persistence.
     * 
     * Requires: FILE_NAME must be a valid file path
     * Modifies: books.txt file
     * Effects: Writes each book's data to the file
     */

    public void saveBooks(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book b : this.getBooks()) {
                writer.write(b.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }
    
    /**
     * Loads Books from file into books object
     * Creates a file if it doesn't exist
     * 
     * Requires: 
     * Modifies: this.books, FileSystem
     * Effects: Populates books from file
     */
     public void loadBooks() {
         try( BufferedReader reader = new BufferedReader(new FileReader("books.txt"))){
         String line;
         while((line = reader.readLine()) != null){
             String[] parameters = line.split(",");
             this.books.add(new Book(parameters[0],Double.parseDouble(parameters[1])));
         }
             
        }catch(IOException e){System.out.println("Error Loading Books");}
    }

}
    
    

