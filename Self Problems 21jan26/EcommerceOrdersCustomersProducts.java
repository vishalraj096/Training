import java.util.*;

class Product {
    private final String name;
    private final double unitPrice;

    Product(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return name + "($" + unitPrice + ")";
    }
}

class OrderItem {
    private final Product product;
    private final int quantity;

    OrderItem(Product product, int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("quantity must be positive");
        this.product = product;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getUnitPrice() * quantity;
    }

    @Override
    public String toString() {
        return quantity + " x " + product.getName() + " = $" + getSubtotal();
    }
}

class Customer4 {
    private final String name;

    Customer4(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void placeOrder(Order order) {
        order.setCustomer(this);
        System.out.println(name + " placed an order.");
    }
}

class Order {
    private Customer4 customer;
    private final List<OrderItem> items = new ArrayList<>();

    public void setCustomer(Customer4 customer) {
        this.customer = customer;
    }

    public Customer4 getCustomer() {
        return customer;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double total() {
        return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }
}

public class EcommerceOrdersCustomersProducts {
    public static void main(String[] args) {
        Product apples = new Product("Apples", 3.0);
        Product milk = new Product("Milk", 2.0);

        Customer4 alice = new Customer4("Alice");
        Order order = new Order();
        alice.placeOrder(order);

        order.addItem(new OrderItem(apples, 2));
        order.addItem(new OrderItem(milk, 1));

        System.out.println("Order summary for " + order.getCustomer().getName() + ":");
        for (OrderItem item : order.getItems()) {
            System.out.println("  - " + item);
        }
        System.out.println("Total: $" + order.total());
    }
}
