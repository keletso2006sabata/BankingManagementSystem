package model;

public abstract class Account {

    private String accountNumber;
    private double balance;
    private String branch;

    public Account(String accountNumber, double balance, String branch) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.branch = branch;
        if  (balance >= 0 && Double.isFinite(balance)){
            this.balance = balance;
        } else {throw new IllegalArgumentException("Invalid opening balance");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
             balance = balance + amount;
            System.out.println("Deposit successful");
        } else {
            System .out.println("Invalid deposit amount");
        }
    }
    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public String getBranch(){
        return branch;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", branch='" + branch + '\'' +
                '}';
    }
}
