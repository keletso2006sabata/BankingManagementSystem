package model;

public class SavingsAccount extends Account implements InterestBearing{

    private double interestRate = 0.0005;

    public SavingsAccount(String accountNumber, double balance, String branch) {
        super(accountNumber, balance, branch);
    }
    @Override
    public double calculateInterest(){
        return getBalance()*interestRate;
    }

    @Override
     public void deposit (double amount) {
        super.deposit(amount);
}
     @Override
    public void withdraw (double amount) {
        System.out.println("Withdrawals are not allowed from Savings Account");
     }
     @Override
    public String toString(){
        return "Savings Account\n" + super.toString();
     }
}
