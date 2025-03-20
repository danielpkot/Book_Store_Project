package project;


public class SilverState implements CustomerState {
   // gotta do this
    
    @Override
    public double buy(double price){
        return 1.00;
    }
    
    @Override
    public int redeem(int points){
        return 1;
    }
    
}
