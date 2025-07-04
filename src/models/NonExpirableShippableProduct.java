package models;

/**
 * Represents a product that is shippable and does not have an expiry date.
 * This class extends the Product class and implements the Shippable interface.
 */
public class NonExpirableShippableProduct extends Product implements Shippable {

    private double weight;

    public NonExpirableShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
