package project;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.*;

public class BookManager {
    //Overview: Book manager used for adding/removing books from
    //          the list of books, also used for retrieving the list
    //           of books
    
    private final static String FILE_NAME = "books.txt";
    
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
        for (Book b1 : books) {
            if (b.getName().equals(b1.getName())) {
                System.out.println("Book already exists!");
                return;
            }
        }
        books.add(b);
    }
    
    //Modifies: this.books
    //
    //Effects: Removes a book from the books arraylist
    public void Remove(Book b){
        books.remove(b);
    }
    
    
    //Not sure how to write specifications for this, since the only thing
    //we are modifying is a file.
    public void saveBooks(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                writer.write(book.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }
    
    //Not sure how to write specifications for this, since the only thing
    //we are modifying is a file.
    public void loadBooks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        
        books.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                books.add(new Book(parts[0], Double.parseDouble(parts[1])));
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }
}
    
    

