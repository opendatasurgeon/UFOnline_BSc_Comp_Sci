public class SpecialSavingsAccount extends BankAccount
{
    private double interestRateDoubled;
    
    public SpecialSavingsAccount () { 
        //super();
       // System.out.println("SpecialSavingsAccount default Constructor.");
        this.interestRateDoubled = 2.0;
    }
    
    public SpecialSavingsAccount (double rate) { 
    	
    	//super(rate);
        //System.out.println("SpecialSavingsAccount parametrized Constructor.");
        this.interestRateDoubled = rate;
    }

//    public void doublyAddInterest()
//    {
//         double interest = 2 * getBalance() * 
//						interestRate / 100;
//						
//         deposit(interest);
//         
//    }
    
    @Override
    public void print()
    {
    	System.out.println("SpecialSavingsAccount print().");
         
    }
    
    public void printSomethingElse()
    {
    	System.out.println("SpecialSavingsAccount printSomethingElse().");
         
    }
    
}