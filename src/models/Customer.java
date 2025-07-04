package models;

import exceptions.InsufficientBalanceException;

public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Checks if the customer can afford a purchase of the specified amount.
     *
     * @param amount The amount to check against the customer's balance.
     * @return true if the customer can afford the purchase, false otherwise.
     */
    public boolean canAfford(double amount) {
        return balance >= amount;
    }

    /**
     * Makes a purchase by deducting the specified amount from the customer's balance.
     *
     * @param amount The amount to deduct from the customer's balance.
     * @throws IllegalArgumentException if the customer cannot afford the purchase.
     */
    public void makePurchase(double amount) {
        if (!canAfford(amount)) {
            throw new InsufficientBalanceException();
        }
        balance -= amount;
    }
}
