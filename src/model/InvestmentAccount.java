package model;

public class InvestmentAccount extends Account implements InterestBearing {

    private double interestRate = 0.05;
    private double initialDeposit;

    public InvestmentAccount(String accountNumber, double balance, String branch) {
        super(accountNumber, balance, branch);

        if (balance < 500) {
            throw new IllegalArgumentException("Minimum opening deposit is BWP 500");
        }
        this.initialDeposit = balance;
    }
    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;

    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
    }
    @Override
    public void withdraw (double amount){
        if (Double.isFinite(amount) &&amount > 0 && amount <= getBalance()){
    setBalance(getBalance() - amount);
    System.out.println("Withdrawal successful");
        }

        else{
            System.out.println("Insufficient funds or invalid amount");
        }
    }
    @Override
    public String toString(){
        return "Investment Account\n" + super.toString();
    }
}
