class MockConnection {

    private boolean open;

    public void open() {
        open = true;
        System.out.println("Connection opened.");
    }

    public void sendData(String data) {
        if (!open) {
            throw new IllegalStateException("Connection is not open");
        }
        if (data == null) {
            throw new RuntimeException("Data cannot be null");
        }
        System.out.println("Sending data: " + data);
    }

    public void close() {
        if (open) {
            System.out.println("Connection closed.");
            open = false;
        } else {
            System.out.println("Connection already closed.");
        }
    }
}

public class ConnectionManager {

    public void useConnection() {
        MockConnection connection = new MockConnection();
        try {
            connection.open();
            // This will throw RuntimeException to simulate failure
            connection.sendData(null);
        } catch (RuntimeException e) {
            System.err.println("Error while using connection: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.err.println("Error while closing connection: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ConnectionManager manager = new ConnectionManager();
        manager.useConnection();
    }
}
