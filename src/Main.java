import model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<Account>();

        Customer customer = new Customer(123,"Blessing","Kagi","PO Box Maitengwe", "75147393", "KSabata@gmail.com",accounts );

        SavingsAccount Savings = new SavingsAccount("S123", 1000, "Francistown");

        InvestmentAccount Investment = new InvestmentAccount("I123", 500, "Francistown");

        ChequeAccount Cheque = new ChequeAccount("C123", 1500, "Francistown","BBS Company", "Gaborone");

        customer.addAccount(Savings);
        customer.addAccount(Investment);
        customer.addAccount(Cheque);
        Savings.deposit(200);
        Savings.withdraw(100);

        Investment.deposit(300);
        Investment.withdraw(100);

        Cheque.deposit(500);
        Cheque.withdraw(200);

        System.out.println("Savings Interest: "
                + Savings.calculateInterest());

        System.out.println("Investment Interest: "
                + Investment.calculateInterest());

        for (Account account : customer.getAccounts(Account)){
            System.out.println(account);
        }

    }

}