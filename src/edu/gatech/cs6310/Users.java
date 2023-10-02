package edu.gatech.cs6310;

public class Users {
    private String accountID; // Unique
    private String firstName;
    private String lastName;
    private String phoneNum;
    public Users(String accountID, String firstName, String lastName, String phoneNum) {
        this.accountID = accountID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getName() {
        return String.format("%s_%s", firstName, lastName);
    }
}
