# Fawry Rise Journey – Full Stack Development Internship Challenge

## E-Commerce System

This project is a Java-based e-commerce system designed as part of the Fawry Rise Journey Full Stack Development Internship Challenge. It demonstrates object-oriented design, exception handling, and covers various real-world e-commerce scenarios.

---

## Features

- **Product Definition**: Products have a name, price, and quantity.
- **Product Types**:
  - **Expirable Products**: e.g., Cheese, Biscuits (with expiry date).
  - **Non-Expirable Products**: e.g., TV, Mobile (no expiry).
  - **Shippable Products**: e.g., Cheese, TV (with weight).
  - **Non-Shippable Products**: e.g., Mobile scratch cards.
- **Cart Functionality**:
  - Add products to cart with a specific quantity (not exceeding available stock).
  - Remove products from cart.
  - View cart contents and total price.
- **Checkout Process**:
  - Prints checkout details: order subtotal, shipping fees, total paid amount, and customer balance after payment.
  - Handles errors for empty cart, insufficient balance, out-of-stock, or expired products.
  - Collects shippable items and sends them to a shipping service.
- **Shipping Service**:
  - Accepts a list of shippable items (implementing `getName()` and `getWeight()` methods).
  - Prints shipment notice with item details and total package weight.
- **Exception Handling**:
  - Custom exceptions for all error cases (empty cart, out of stock, expired, insufficient balance, invalid quantity, etc.).

---

## Assumptions

- All prices and balances are in the same currency (e.g., EGP).
- Shipping fee is a flat rate (30) if there are any shippable items; otherwise, it is 0.
- Product weights are in kilograms (kg).
- Expiry is checked at checkout time.
- The system is console-based and not connected to a database or UI.
- The main entry point is `App.java`.

---

## Project Structure

```
src/
  models/         # Product, Cart, Customer, and product type classes
  services/       # CheckoutService, ShippingService
  exceptions/     # Custom exception classes
  App.java        # Main application entry point
```

---

## How to Run

1. **Compile the project** (from the project root):
   ```sh
   javac -d out src/models/*.java src/services/*.java src/exceptions/*.java src/App.java
   ```
2. **Run the application**:
   ```sh
   java -cp out App
   ```

---

## Usage Example

The following code (from `App.java`) demonstrates the main use cases:

```java
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
```

### Console Output Example

```
** Shipment notice **
2x Cheese        400g
1x Biscuits      700g
Total package weight 1.1kg

** Checkout receipt **
2x Cheese        200
1x Biscuits      150
3x TV            9000
1x Scratch Card  50
----------------------
Subtotal         9400
Shipping         30
Amount           9430
Remaining Balance 570
```

---

## Error Handling & Edge Cases

- **Expired Product**: Throws an error if a product in the cart is expired.
- **Out of Stock**: Throws an error if requested quantity exceeds available stock.
- **Insufficient Balance**: Throws an error if the customer cannot afford the total amount.
- **Empty Cart**: Throws an error if checkout is attempted with an empty cart.

---

## Extending the System

- Add new product types by extending the `Product` class and/or implementing the `Shippable` interface.
- Add new business rules by modifying or extending the service classes.

---

## Author & License

- Developed for the Fawry Rise Journey Internship Challenge.
- For educational/demo purposes only.
