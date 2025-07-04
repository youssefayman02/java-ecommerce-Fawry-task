package exceptions;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String productName) {
        super(productName + " is out of stock.");
    }
}
