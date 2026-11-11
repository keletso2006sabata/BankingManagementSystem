
package util;

import model.Customer;
import model.Account;
import model.Transaction;
import java.util.ArrayList;
import java.util.List;

public class BankData {
    public static List<Customer> customers = new ArrayList<>();
    public static List<Account> accounts = new ArrayList<>();

    public static List<Transaction> transactions = new ArrayList<>();

    public static Account findAccount(String accountNumber) {
        System.out.println("Searching for: " + accountNumber);
        System.out.println("Accounts stored: " + accounts.size());

        for (Account account : accounts) {

            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }


    public static void addTransaction(String accountNumber, String type, double amount) {

        int id = transactions.size() + 1;

        Transaction transaction = new Transaction(
                id,
                accountNumber,
                type,
                amount,
                java.time.LocalDateTime.now()
        );

        transactions.add(transaction);
    }


    public static void applyMonthlyInterest() {

        System.out.println("Accounts found: " + accounts.size());

        for (Account account : accounts) {

            System.out.println("Checking account: " + account.getAccountNumber());

            if (account instanceof model.InterestBearing) {

                model.InterestBearing interestAccount =
                        (model.InterestBearing) account;

                double interest = interestAccount.calculateInterest();

                account.setBalance(account.getBalance() + interest);

                addTransaction(
                        account.getAccountNumber(),
                        "Monthly Interest",
                        interest
                );

                System.out.println("Interest added: BWP " + interest);

            } else {
                System.out.println("This account does not earn interest");
            }
        }
    }

}

