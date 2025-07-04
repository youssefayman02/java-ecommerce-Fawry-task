package models;

import exceptions.InvalidQuantityException;
import exceptions.InvalidShippingWeightException;
import exceptions.NotShippableException;
import exceptions.OutOfStockException;

public abstract class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
        * Increments the quantity of the product by a specified amount.
        * @param amount the amount to increment the quantity by.
        * @throws InvalidQuantityException if the amount is less than or equal to zero.
        * @throws OutOfStockException if the resulting quantity exceeds the maximum allowed stock.
        */
    public void decrementQuantity(int amount) {
        if (amount <= 0) {
            throw new InvalidQuantityException();
        }
        if (quantity < amount) {
            throw new OutOfStockException(name);
        }
        quantity -= amount;
    }

    /**
     * Checks if the product is expired.
     * This method should be implemented by subclasses to provide specific expiration logic to handle expired products.
     * @return true if the product is expired, false otherwise.
     */
    public abstract boolean isExpired();

    @Override
    public String toString() {
        return "models.Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
