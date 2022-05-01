public class SavingsAccount extends BankAccount
{
    protected double interestRate;
    
    public SavingsAccount () { 
        //super();
        //System.out.println("SavingsAccount default Constructor.");
        this.interestRate = 0.0;
    }
    
    public SavingsAccount (double rate) { 
        super(rate);
        //System.out.println("SavingsAccount parametrized Constructor.");
        this.interestRate = rate;
    }

    public void addInterest()
    {
         double interest = getBalance() * 
						interestRate / 100;
						
         deposit(interest);
         
    }
    
    public void print()
    {
    	System.out.println("SavingsAccount print().");
         
    }
}