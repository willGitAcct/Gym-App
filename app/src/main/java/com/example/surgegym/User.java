package com.example.surgegym;

public class User {
    public String stringFirstName, stringLastName, stringEmail, stringPhoneNumber, stringDateBirth , stringGender ,stringAddress, packageChoice;
    public int memberNum;

    public User(){

    }

    public User(String firstName, String lastName, String email, String phone, String birthDate, String gender, String address, String pack, int num){
        this.stringFirstName = firstName;
        this.stringLastName = lastName;
        this.stringEmail = email;
        this.stringPhoneNumber = phone;
        this.stringDateBirth = birthDate;
        this.stringGender = gender;
        this.stringAddress = address;
        this.packageChoice = pack;
        this.memberNum = num;

    }
}
