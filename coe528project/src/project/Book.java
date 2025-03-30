package project;

import javafx.scene.control.CheckBox;

/*
 * Abstraction Function:
 * AF(b) = A Book object where:
 *         - b.name represents the title of the book
 *         - b.price represents the cost of the book in dollars
 *
 * Representation Invariant:
 * RI(b) = true if:
 *         - b.name ≠ null
 *         - b.name is not an empty string
 *         - b.price ≥ 0
 */
public class Book {
    //Overview:Book class, representing a book with 
    //         a name, author and price
    
    private String name;
    private double price;
    private CheckBox select; 
    
    
    /*
     * Requires: name is not null, price >= 0
     * Modifies: this
     * Effects: Initializes the book with the specified name and price
     */
    public Book(String name, double price){
        this.name = name;
        this.price = price;
        this.select = new CheckBox();
        
    }
    
    /*
     * Requires: none
     * Modifies: none
     * Effects: Returns the name of the book
     */
    public String getName(){
        return this.name;
    }
    
    /*
     * Requires: none
     * Modifies: none
     * Effects: Returns the price of the book
     */
    public double getPrice(){
        return price;
    }
    
    /*
     * Requires: none
     * Modifies: none
     * Effects: Returns the selection status of the book
     *          checking if the box is checked
     */
    public CheckBox getSelect(){
        return select;
    }
    
    /*
     * Requires: none
     * Modifies: none
     * Effects: sets the selection status of the 
     *          checkbox associated with the book
     */
    public void setSelect(CheckBox select){
        this.select = select;
    }
    
    /*
     * Requires: none
     * Modifies: none
     * Effects: Returns a string in the form "name,price"
     */
    @Override
    public String toString(){
        return name + "," + price;
    }
}
    

