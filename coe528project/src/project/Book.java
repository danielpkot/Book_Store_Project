package project;


public class Book {
    //Overview:Book class, representing a book with 
    //         a name, author and price
    
    private String name;
    private double price;
    
    
    //Modifies: this
    //Effects: Sets this.name as name and
    //         this.price as price
    public Book(String name, double price){
        this.name = name;
        this.price = price;
        
    }
    
    //Effects: Returns name of book
    public String getName(){
        return this.name;
    }
    
    //Effects: Returns price of book
    public double getPrice(){
        return price;
    }
}
    

