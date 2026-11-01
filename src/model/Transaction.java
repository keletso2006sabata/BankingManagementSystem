package model;

import java.time.LocalDateTime;

public class Transaction {

    private int transactionID;
    private String transactionType;
    private double amount;
    private LocalDateTime transactionDate;

    public Transaction(int transactionID, String transactionType, double amount, LocalDateTime transactionDate) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }
    public void record(){
        System.out.println("Transaction recorded successfully");
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Transaction Type: " + transactionType);
        System.out.println("Amount: BWP " + amount);
        System.out.println("Date: " + transactionDate);
    }

    public int getTransactionID() {
        return transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
