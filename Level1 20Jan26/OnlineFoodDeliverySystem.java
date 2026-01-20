import java.util.*;

abstract class FoodItem {
    private String itemName;
    private double price;
    private int quantity;

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public abstract double calculateTotalPrice();
    public String getItemDetails() {
        return "Item: " + itemName + ", Price: " + price + ", Quantity: " + quantity;
    }
}

interface Discountable {
    double applyDiscount();
    String getDiscountDetails();
}

class VegItem extends FoodItem implements Discountable {
    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }
    @Override
    public double calculateTotalPrice() { return getPrice() * getQuantity() - applyDiscount(); }
    @Override
    public double applyDiscount() { return getPrice() * getQuantity() * 0.10; }
    @Override
    public String getDiscountDetails() { return "Veg Discount: 10%"; }
}

class NonVegItem extends FoodItem implements Discountable {
    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }
    @Override
    public double calculateTotalPrice() { return getPrice() * getQuantity() + 20 - applyDiscount(); }
    @Override
    public double applyDiscount() { return getPrice() * getQuantity() * 0.05; }
    @Override
    public String getDiscountDetails() { return "Non-Veg Discount: 5% + Rs.20 extra charge"; }
}

public class OnlineFoodDeliverySystem {
    public static void processOrder(List<FoodItem> items) {
        for (FoodItem item : items) {
            System.out.println(item.getItemDetails());
            System.out.println("Total Price: " + item.calculateTotalPrice());
            if (item instanceof Discountable discountable) {
                System.out.println(discountable.getDiscountDetails());
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        List<FoodItem> order = new ArrayList<>();
        order.add(new VegItem("Paneer Tikka", 150, 2));
        order.add(new NonVegItem("Chicken Curry", 200, 1));
        processOrder(order);
    }
}
