package com.drinkshop.model;

public class EnumForEntity {

    public enum UserType{
        admin, manager, employee, customer
    }

    public enum OrderType{
        takeAway, shipping
    }

    public enum OrderStatus{
        processed, ready, shipping, completed, cancelled
    }

    public enum PaymentType{
        cash, bank
    }

    public enum DrinkSize{
        S(0),M(1),L(2);
        private final int value;

        DrinkSize(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
