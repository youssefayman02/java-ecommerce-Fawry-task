package exceptions;

public class InvalidShippingWeightException extends RuntimeException {
    public InvalidShippingWeightException(String productName) {
        super("Non-shippable product '" + productName + "' cannot have a weight.");
    }
}
