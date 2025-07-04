package models;

import exceptions.InvalidQuantityException;
import exceptions.OutOfStockException;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private final Map<Product, Integer> items = new HashMap<>();

    /**
     * Adds a product to the cart with the specified quantity.
     *
     * @param product The product to add.
     * @param quantity The quantity of the product to add.
     */
    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }

        if (product.getQuantity() < quantity) {
            throw new OutOfStockException(product.getName());
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    /**
     * Removes a product from the cart.
     *
     * @param product The product to remove.
     */
    public void removeProduct(Product product) {
        if (!items.containsKey(product)) {
            throw new IllegalArgumentException("models.Product not found in cart: " + product.getName());
        }
        items.remove(product);
    }

    /**
     * Get all items in the cart.
     * @return A map of products and their quantities in the cart.
     */
    public Map<Product, Integer> getItems() {
        return items;
    }

    /**
     * Checks if the cart is empty.
     *
     * @return true if the cart is empty, false otherwise.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Clears all items from the cart.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Returns the total price of all items in the cart.
     *
     * @return The total price.
     */
    public double getTotalPrice() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }
}
