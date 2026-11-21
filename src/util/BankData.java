
package util;

import model.Customer;
import model.Account;
import model.Transaction;
import model.InterestBearing;

import dao.AccountDAO;
import dao.TransactionDAO;

import java.util.ArrayList;
import java.util.List;

public class BankData {

    public static List<Customer> customers = new ArrayList<>();
    public static List<Account> accounts = new ArrayList<>();
    public static List<Transaction> transactions = new ArrayList<>();

    public static Account findAccount(String accountNumber) {

        for (Account account : accounts) {

            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

    public static void addTransaction(
            String accountNumber,
            String type,
            double amount) {

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

        AccountDAO accountDAO = new AccountDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

        System.out.println("Accounts found: " + accounts.size());

        for (Account account : accounts) {

            if (account instanceof InterestBearing) {

                InterestBearing interestAccount =
                        (InterestBearing) account;

                double interest = interestAccount.calculateInterest();

                if (interest > 0) {

                    double newBalance = account.getBalance() + interest;

                    // Update balance in MySQL
                    boolean balanceUpdated = accountDAO.updateBalance(
                            account.getAccountNumber(),
                            newBalance
                    );

                    if (balanceUpdated) {

                        // Update balance in memory
                        account.setBalance(newBalance);

                        // Save transaction in MySQL
                        boolean transactionSaved =
                                transactionDAO.recordTransaction(
                                        account.getAccountNumber(),
                                        "Monthly Interest",
                                        interest
                                );

                        // Add transaction to memory
                        addTransaction(
                                account.getAccountNumber(),
                                "Monthly Interest",
                                interest
                        );

                        System.out.println(
                                "Interest added: BWP " + interest
                        );

                        System.out.println(
                                "New Balance: BWP " + newBalance
                        );

                    } else {

                        System.out.println(
                                "Balance update failed for account: "
                                        + account.getAccountNumber()
                        );
                    }

                } else {

                    System.out.println(
                            "No interest to apply."
                    );
                }

            } else {

                System.out.println(
                        "This account does not earn interest: "
                                + account.getAccountNumber()
                );
            }
        }
    }
}