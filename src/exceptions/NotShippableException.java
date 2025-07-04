package exceptions;

public class NotShippableException extends RuntimeException {
    public NotShippableException(String productName) {
        super(productName + "' is not shippable.");
    }
}
