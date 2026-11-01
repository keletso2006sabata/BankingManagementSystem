package view;
import model.*;
public class Main {
    public static void main(String[] args){

        Customer customer = new Customer(321, "Blessing", "Sabs", "PO Box Maitengwe", "75232618", "B.Sabs@gmail.com");

        SavingsAccount savings = new SavingsAccount("S562", 1000, "Francistown");

        InvestmentAccount investment = new InvestmentAccount("I987", 500, "Francistown");

        ChequeAccount cheque = new ChequeAccount("C524", 1500, "Francistown", "BBS Company", "Gaborone");

        customer.addAccount(savings);
        customer.addAccount(investment);
        customer.addAccount(cheque);

        System.out.println("=======SAVINGS ACCOUNTS =======");
        savings.deposit(200);
        savings.withdraw(100);

        System.out.println("\n====== INVESTMENT ACCOUNTS =======");
        investment.deposit(300);
        investment.withdraw(100);

        System.out.println("\n=====CHEQUE ACCOUNTS =====");
        cheque.deposit(500);
        cheque.withdraw(200);

        System.out.println("\n===== MONTHLY INTEREST =====");

        System.out.println("Savings Interest: BWP " + savings.calculateInterest());

        System.out.println("Investment Interest: BWP " + investment.calculateInterest());

        System.out.println("\n===== CUSTOMER DETAILS ======");
        System.out.println(customer);

        System.out.println("\n===== CUSTOMER ACCOUNTS =====");

        for (Account account : customer.getAccounts()){
            System.out.println(account);
            System.out.println("-----------------");
        }

        //TEST THE TRANSACTION CLASS
        System.out.println("\n===== TRANSACTION RECORD ======");

        Transaction transaction = new Transaction(1, "Deposit", 200, java.time.LocalDateTime.now());
        transaction.record();

        //TEST TELLER CLASS
        System.out.println("\n===== BANK TELLER =====");

        Teller teller = new Teller(190, "Taelo", "Chin", "72543762", "Teller", "Francistown", "Mpho", "demoPassword");

        teller.registerCustomer(customer);

        //TEST MANAGER CLASS
        System.out.println("\n===== BANK MANAGER =====");

        Manager manager = new Manager(432, "Kea", "Thomas", "72381463", "Manager", "Francistown", "Kea", "demoPassword");

        manager.viewReports();
        manager.manageCustomerRecords();
    }
}
