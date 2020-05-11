package se.kth.iv1350.retailpos.integration;

/**
 * Throws an exception when the database can not be reached. This is simulated
 * since there is no real database in this implementation. By searching for 
 * item identifier '999999' this exception will be thrown.
 */
public class DatabaseFailureException extends RuntimeException{
    /**
     * Creates a a new instance.
     * 
     * @param message The message that describes the error.
     */
    public DatabaseFailureException(String message) {
        super(message);
    }
}
