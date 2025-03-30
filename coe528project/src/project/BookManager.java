package project;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.*;


/*
 * Abstraction Function:
 * AF(m) = A BookManager object m where:
 *         - m.books is the list of all Book objects
 *         - The file "books.txt" is used for storing the book data
 *         - The books are stored in insertion order
 *
 * Representation Invariant:
 * RI(m) = true if:
 *         - m.books ≠ null
 *         - For all integers i, j (0 ≤ i < j < m.books.size()),
 *           m.books.get(i).getName() ≠ m.books.get(j).getName()
 *         - FILE_NAME refers to a valid accessible path
 */

public class BookManager {
    //Overview: Book Manager class for managing all the book objects
    
    
    private final static String FILE_NAME = "books.txt";
    private static BookManager instance = null;
    private ArrayList<Book> books;
    
     /*
     * Requires: none
     * Modifies: this
     * Effects: Initializes the books list to empty
     */
    private BookManager(){
        books = new ArrayList<Book>();
    }
    
     /*
     * Requires: none
     * Modifies: instance if null
     * Effects: Returns the singleton instance of the book manager
     */
    public static BookManager getInstance(){
        if (instance == null){
            instance = new BookManager();
        }
        return instance;
    }
    
    
     /*
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
     * Requires:  none
     * Modifies: this.books
     * Effects: Adds the book to the list if not already present
     */

    public void addBook(Book b){
        if(b == null){ return;}
        for (Book b1 : books) {
            if (b.getName().trim().equals(b1.getName().trim())) {
                System.out.println("Book already exists!");
                return;
            }
        }
        if(b.getName().isEmpty() || b.getPrice() <= 0){
            System.out.println("Name can't be empty/Price can't be 0");
            return;
        }
        books.add(b);
        System.out.println("Book added: " + b.getName() + " ($" + b.getPrice() + ")");
    }
    
    /*
     * Requires: 
     * Modifies: this.books
     * Effects: Removes the specified book from the list, if it's in the list
     */
    public void remove(Book b){
        if(b== null){ return;}
        books.remove(b);
        System.out.println("if boox exists, Book removed: " + b.getName());
    }
    
    
   /**
     * Saves all books to a file for persistence.
     *
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
     * Requires: books.txt exists in the file path
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
    
    

