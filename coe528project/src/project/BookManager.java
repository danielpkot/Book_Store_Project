package project;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.*;

public class BookManager {
    //Overview: Book manager used for adding/removing books from
    //          the list of books, also used for retrieving the list
    //           of books
    
    private final static String filename = "books.txt";
    
    private static BookManager instance = null;
    
    private ArrayList<Book> books;
    
    
    //Effects: Instantiates a BookManager object
    private BookManager(){
     
    }
    
    //Effects: Returns the single instance of the BookManager object, if
    //         no instance is instantiated it creates one.
    public BookManager getInstance(){
        if (instance == null){
            instance = new BookManager();
        }
        return instance;
    }
    
    
    //Effects: Returns arraylist containing all books
    public ArrayList<Book> getBooks(){
        return books;
    }
    
    
    //Modifies: this.books
    //
    //Effects: Adds a book to the books arraylist
    public void addBook(Book b){
        books.add(b);
    }
    
    //Modifies: this.books
    //
    //Effects: Removes a book from the books arraylist
    public void Remove(Book b){
        books.remove(b);
    }
    
    // Saving this for later gonna need to test this alot I feel like
    public void loadBooks(){
        
    }
    
    
    public void saveBooks(){
        try {
            FileWriter writer = new FileWriter(filename,true);
            for(Book b : books){
                writer.write(b.getName() + " " + b.getPrice() + "\n" );
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    
}
