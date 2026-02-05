class TransactionProcessingException extends Exception {
    public TransactionProcessingException(String message) {
        super(message);
    }

    public TransactionProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class TransactionProcessor {

    public void validateTransaction(String userId, double amount) throws TransactionProcessingException {
        if (userId == null || userId.isEmpty()) {
            throw new TransactionProcessingException("User ID must not be empty");
        }
        if (amount <= 0) {
            throw new TransactionProcessingException("Amount must be positive");
        }
    }

    public void processTransaction(String userId, double amount) throws TransactionProcessingException {
        try {
            validateTransaction(userId, amount);
            System.out.println("Transaction successful for user " + userId + " amount " + amount);
        } catch (TransactionProcessingException e) {
            // Rethrow with additional context
            throw new TransactionProcessingException(
                    "Error processing transaction for user " + userId + " with amount " + amount + ": " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        TransactionProcessor processor = new TransactionProcessor();

        try {
            // This will fail because amount is negative
            processor.processTransaction("user123", -500);
        } catch (TransactionProcessingException e) {
            System.err.println("Transaction failed: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Original cause: " + e.getCause().getMessage());
            }
        }
    }
}
