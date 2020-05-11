package se.kth.iv1350.retailpos.controller;

/**
 * Thrown when an operation failed for an unknown reason.
 */
public class OperationFailedException extends Exception {
    /**
     * Creates an instance with the message and the cause.
     *
     * @param message   The message for the exception.
     * @param cause The exception that caused this exception.
     */
    public OperationFailedException(String message, Exception cause) {
        super(message, cause);
    }
}
