package project;

import javafx.scene.control.CheckBox;


public class Book {
    //Overview:Book class, representing a book with 
    //         a name, author and price
    
    private String name;
    private double price;
    private CheckBox select; 
    
    
    /**
     * Constructs a new Book object with a given name and price.
     * 
     * Requires: name is not null, price >= 0
     * Modifies: this
     * Effects: Initializes the book with the specified name and price
     */
    public Book(String name, double price){
        this.name = name;
        this.price = price;
        this.select = new CheckBox();
        
    }
    
    /**
     * Gets the name (title) of the book.
     * 
     * Requires: none
     * Modifies: none
     * Effects: Returns the name of this book
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Gets the price of the book.
     * 
     * Requires: none
     * Modifies: none
     * Effects: Returns the price of this book
     */
    public double getPrice(){
        return price;
    }
    
    
    public CheckBox getSelect(){
        return select;
    }
    
    public void setSelect(CheckBox select){
        this.select = select;
    }
    
    /**
     * Converts this book into a string format for file storage.
     * 
     * Requires: none
     * Modifies: none
     * Effects: Returns a string in the form "name,price"
     */
    @Override
    public String toString(){
        return name + "," + price;
    }
}
    

