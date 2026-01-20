class Order {
    String orderId;
    String orderDate;

    Order(String orderId, String orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }
}

class ShippedOrder extends Order {
    String trackingNumber;

    ShippedOrder(String orderId, String orderDate, String trackingNumber) {
        super(orderId, orderDate);
        this.trackingNumber = trackingNumber;
    }
}

class DeliveredOrder extends ShippedOrder {
    String deliveryDate;

    DeliveredOrder(String orderId, String orderDate, String trackingNumber, String deliveryDate) {
        super(orderId, orderDate, trackingNumber);
        this.deliveryDate = deliveryDate;
    }

    String getOrderStatus() {
        return "Delivered on " + deliveryDate;
    }
}

public class OnlineRetailOrderManagement {
    public static void main(String[] args) {
        DeliveredOrder d = new DeliveredOrder("ORD001", "2026-01-19", "TRK12345", "2026-01-20");
        System.out.println("Order ID: " + d.orderId);
        System.out.println("Order Date: " + d.orderDate);
        System.out.println("Tracking Number: " + d.trackingNumber);
        System.out.println(d.getOrderStatus());
    }
}
