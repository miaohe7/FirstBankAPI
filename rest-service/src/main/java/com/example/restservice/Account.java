package com.example.restservice;

public class Account {
    private String accountNumber;
    private Double balance;
    private String customerEmail;
    private String phonenumber;

    public Account(String accountNumber, String customerEmail, String phonenumber){
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.customerEmail = customerEmail;
        this.phonenumber = phonenumber;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public double getBalance(){
        return balance;
    }
    public String getCustomerEmail(){
        return customerEmail;
    }
    public String getPhonenumber(){
        return phonenumber;
    }
    @Override
    public String toString(){
        return "";
    }
}
