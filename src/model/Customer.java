package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int CustomerID;
    private String firstName;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;

    private List<Account> accounts = new ArrayList<>();

    public Customer(int customerID, String firstName, String surname, String address, String phoneNumber, String email) {
        this.CustomerID = customerID;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accounts = accounts;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {

        this.CustomerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getSurname() {

        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public List<Account> getAccounts() {

        return accounts;
    }
    public void addAccount(Account account){
        if(account != null && !accounts.contains(account)){
            accounts.add(account);
        }
    }
    public void removeAccount(Account account){
        accounts.remove(account);
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerID=" + CustomerID +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
