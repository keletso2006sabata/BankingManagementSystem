package model;

public class ChequeAccount extends Account {

    private String companyName;
    private String companyAddress;

    public ChequeAccount(String accountNumber, double balance, String branch, String companyName, String companyAddress) {
        super(accountNumber, balance, branch);


        if (companyName == null || companyName.isBlank()
                || companyAddress == null || companyAddress.isBlank()) {
            throw new IllegalArgumentException("Company name and address are required");
        }
            this.companyName = companyName;
            this.companyAddress = companyAddress;


    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        if (Double.isFinite (amount) && amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawal successful");
        } else {
            System.out.println("Insufficient funds or invalid amount");
        }
    }

    @Override
    public String toString() {
        return "Cheque Account\n" + super.toString()
                + "\nCompany: " + companyName
                + "\nCompany Address: " + companyAddress;
    }
    }


