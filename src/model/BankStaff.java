package model;

public class BankStaff {
    private int staffID;
    private String firstName;
    private String surname;
    private String contactNumber;
    private String position;
    private String branch;
    private String username;
    private String passwordHash;

    public BankStaff(int staffID, String firstName, String surname, String contactNumber, String position, String branch, String username, String passwordHash) {
        this.staffID = staffID;
        this.firstName = firstName;
        this.surname = surname;
        this.contactNumber = contactNumber;
        this.position = position;
        this.branch = branch;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public int getStaffID() {
        return staffID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getBranch() {
        return branch;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return "BankStaff{" +
                "staffID=" + staffID +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", position='" + position + '\'' +
                ", branch='" + branch + '\'' +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
