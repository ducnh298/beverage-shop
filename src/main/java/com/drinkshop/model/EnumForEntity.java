package com.drinkshop.model;

public class EnumForEntity {

    public enum UserType{
        admin, manager, employee, customer
    }

    public enum OrderType{
        ship, takeAway
    }

    public enum OrderStatus{
        pending, shipping, shipped, cancelled
    }

    public enum PaymentType{
        cod, bank
    }
}
