package model;

public class Manager extends BankStaff{

    public Manager(int staffID, String firstName, String surname, String contactNumber, String position, String branch, String username, String passwordHash ){

        super(staffID, firstName, surname, contactNumber, position, branch, username, passwordHash);

    }
    public void manageCustomerRecords(){
        System.out.println("Manging customer records");
    }
    @Override
    public String toString(){
        return "Manager\n" + super.toString();
    }
}

