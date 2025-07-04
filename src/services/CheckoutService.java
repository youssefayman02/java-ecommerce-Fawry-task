package services;

import exceptions.CartEmptyException;
import exceptions.ExpiredProductException;
import exceptions.InsufficientBalanceException;
import exceptions.OutOfStockException;
import models.Cart;
import models.Customer;
import models.Product;
import models.Shippable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutService {
    private final ShippingService shippingService = new ShippingService();

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new CartEmptyException();

        double subtotal = 0;
        List<Shippable> shippables = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.isExpired()) throw new ExpiredProductException(product.getName());
            if (product.getQuantity() < quantity) throw new OutOfStockException(product.getName());

            subtotal += product.getPrice() * quantity;

            if (product instanceof Shippable) {
                for (int i = 0; i < quantity; i++) {
                    shippables.add((Shippable) product);
                }
            }
        }

        double shippingFee = shippables.isEmpty() ? 0 : 30;
        double total = subtotal + shippingFee;

        if (!customer.canAfford(total)) throw new InsufficientBalanceException();

        customer.makePurchase(total);

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            entry.getKey().decrementQuantity(entry.getValue());
        }

        if (!shippables.isEmpty()) shippingService.shipNotice(shippables);

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            System.out.printf("%dx %-13s %.0f\n", qty, product.getName(), product.getPrice() * qty);
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f\n", subtotal);
        System.out.printf("Shipping         %.0f\n", shippingFee);
        System.out.printf("Amount           %.0f\n", total);
        System.out.printf("Remaining Balance %.0f\n", customer.getBalance());

        cart.clear();
    }
}
