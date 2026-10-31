package model;

public class Teller extends BankStaff {

    public Teller(int staffID, String firstName, String surname, String contactNumber, String position, String branch, String username, String passwordHash) {

        super(staffID, firstName, surname, contactNumber, position, branch, username, passwordHash);
    }

    public void registerCustomer(Customer customer) {
        if (customer != null) {
            System.out.println("Customer registered successfully: "
                    + customer.getFirstName() + " "
                    + customer.getSurname());
        } else {
            System.out.println("Invalid customer details");
        }
    }

    public void openAccount(Customer customer, Account account) {
        if (customer != null && account != null) {
            customer.addAccount(account);
            System.out.println("Account opened successfully");
        } else {
            System.out.println("Invalid customer or account");

        }
    }
        public void recordDeposit (Account account,double amount) {
            if (account != null && Double.isFinite(amount) && amount > 0) {
                account.deposit(amount);
            } else {
                System.out.println("Invalid account or deposit amount");
            }
        }
    }
