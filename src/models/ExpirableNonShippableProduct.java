package models;

import java.time.LocalDate;

/**
 * Represents a product that is non-shippable and has an expiry date.
 * This class extends the Product class.
 */
public class ExpirableNonShippableProduct extends Product {
    private LocalDate expiryDate;

    public ExpirableNonShippableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
