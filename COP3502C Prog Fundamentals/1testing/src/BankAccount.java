public class BankAccount
{
    private double balance;
    
    public BankAccount() {
        
        //System.out.println("Parent's Default Constructor.");
        balance= 0.0; 
        
    }
    
    public BankAccount(double amount) {
        
        this.balance = amount;
        
        //System.out.println("Parent's Parameterized Constructor.");
       
        
    }
    
    public void deposit(double amount) { 
        this.balance = amount;
        
    }
    
    public void withdraw(double amount) {
        
         this.balance -= amount;
         
    }
    
    public double getBalance() {
        
        return this.balance;
        
    }
    
    public void print()
    {
    	System.out.println("BankAccount print().");
         
    }
    
    
}
