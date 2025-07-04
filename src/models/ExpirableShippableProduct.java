package models;

import java.time.LocalDate;

/**
 * Represents a product that is shippable and has an expiry date.
 * This class extends the Product class and implements the Shippable interface.
 */
public class ExpirableShippableProduct extends Product implements Shippable {
    private LocalDate expiryDate;
    private double weight;

    public ExpirableShippableProduct(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
