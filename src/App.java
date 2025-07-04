import models.*;
import services.CheckoutService;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 5, 0.4, LocalDate.now().plusDays(2));
        Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 3, 0.7, LocalDate.now().plusDays(2));
        Product tv = new NonExpirableShippableProduct("TV", 3000, 5, 5.0);
        Product scratchCard = new NonExpirableNonShippableProduct("Scratch Card", 50, 20);

        Customer customer = new Customer("Youssef", 10000);
        Cart cart = new Cart();

        cart.addProduct(cheese, 2);
        cart.addProduct(biscuits, 1);
        cart.addProduct(tv, 3); // optional to match output
        cart.addProduct(scratchCard, 1);

        CheckoutService checkoutService = new CheckoutService();
        checkoutService.checkout(customer, cart);

        // Edge test: expired product
        try {
            Product expiredCheese = new ExpirableShippableProduct("Expired Cheese", 80, 2, 0.4, LocalDate.now().minusDays(1));
            Cart expiredCart = new Cart();
            expiredCart.addProduct(expiredCheese, 1);
            checkoutService.checkout(customer, expiredCart);
        } catch (Exception e) {
            System.out.println("[Expired Test] " + e.getMessage());
        }

        // Edge test: out of stock
        try {
            Cart stockCart = new Cart();
            stockCart.addProduct(tv, 100);
            checkoutService.checkout(customer, stockCart);
        } catch (Exception e) {
            System.out.println("[OutOfStock Test] " + e.getMessage());
        }

        // Edge test: insufficient balance
        try {
            Customer poorCustomer = new Customer("Poor Guy", 10);
            Cart costlyCart = new Cart();
            costlyCart.addProduct(tv, 1);
            checkoutService.checkout(poorCustomer, costlyCart);
        } catch (Exception e) {
            System.out.println("[Insufficient Balance Test] " + e.getMessage());
        }

        // Edge test: empty cart
        try {
            Cart emptyCart = new Cart();
            checkoutService.checkout(customer, emptyCart);
        } catch (Exception e) {
            System.out.println("[Empty Cart Test] " + e.getMessage());
        }
    }
}