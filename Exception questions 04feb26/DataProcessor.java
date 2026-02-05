class DataProcessingException extends Exception {
    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class DataProcessor {

    public void processData(String data) throws DataProcessingException {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }

        if (data.trim().isEmpty()) {
            throw new DataProcessingException("Input data is empty");
        }

        try {
            int value = Integer.parseInt(data.trim());
            System.out.println("Processed value: " + (value * 2));
        } catch (NumberFormatException e) {
            throw new DataProcessingException("Failed to parse integer from data: " + data, e);
        }
    }

    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();

        // unchecked exception
        try {
            processor.processData(null);
        } catch (IllegalArgumentException e) {
            System.err.println("Unchecked exception: " + e.getMessage());
        } catch (DataProcessingException e) {
            System.err.println("Checked exception: " + e.getMessage());
        }

        // checked exception (empty string)
        try {
            processor.processData("   ");
        } catch (IllegalArgumentException e) {
            System.err.println("Unchecked exception: " + e.getMessage());
        } catch (DataProcessingException e) {
            System.err.println("Checked exception: " + e.getMessage());
        }

        // checked exception (invalid number)
        try {
            processor.processData("abc");
        } catch (IllegalArgumentException e) {
            System.err.println("Unchecked exception: " + e.getMessage());
        } catch (DataProcessingException e) {
            System.err.println("Checked exception: " + e.getMessage());
        }

        // successful processing
        try {
            processor.processData("21");
        } catch (IllegalArgumentException | DataProcessingException e) {
            System.err.println("Processing error: " + e.getMessage());
        }
    }
}
