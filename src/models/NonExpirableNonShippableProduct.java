package models;

/**
 * Represents a product that is non-shippable and does not have an expiry date.
 * This class extends the Product class.
 */
public class NonExpirableNonShippableProduct extends Product {

    public NonExpirableNonShippableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}
