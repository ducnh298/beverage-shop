package com.drinkshop.model;

public class EnumForEntity {

    public enum UserType{
        admin, manager, employee, customer
    }

    public enum OrderType{
        shipping, takeAway
    }

    public enum OrderStatus{
        processed, shipping, completed, cancelled
    }

    public enum PaymentType{
        cash, bank
    }
}
