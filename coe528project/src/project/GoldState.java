package project;


public class GoldState implements CustomerState {
    //gotta implement this
    
    
    @Override
    public double buy(double price){
        return 1.00;
    }
    
    @Override
    public int redeem(int points){
        return 1;
    }
    
}
