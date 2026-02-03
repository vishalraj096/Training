import java.util.*;

public class ShoppingCart {
    private final Map<String, Double> productPrices;
    private final LinkedHashMap<String, Integer> cartItems;
    private final TreeMap<Double, List<String>> itemsByPrice;
    
    public ShoppingCart() {
        productPrices = new HashMap<>();
        cartItems = new LinkedHashMap<>();
        itemsByPrice = new TreeMap<>();
    }
    
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Shopping Cart System ===");
            System.out.println("1. Add Product to Catalog");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. Display Cart (Insertion Order)");
            System.out.println("4. Display Products by Price (Sorted)");
            System.out.println("5. Calculate Total");
            System.out.println("6. Remove Item from Cart");
            System.out.println("7. Clear Cart");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1 -> cart.addProductToCatalog(sc);
                case 2 -> cart.addItemToCart(sc);
                case 3 -> cart.displayCart();
                case 4 -> cart.displayProductsByPrice();
                case 5 -> cart.calculateTotal();
                case 6 -> cart.removeItemFromCart(sc);
                case 7 -> cart.clearCart();
                case 8 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    public void addProductToCatalog(Scanner sc) {
        System.out.print("Enter product name: ");
        String productName = sc.nextLine();
        
        System.out.print("Enter product price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        
        productPrices.put(productName, price);
        
        if (!itemsByPrice.containsKey(price)) {
            itemsByPrice.put(price, new ArrayList<>());
        }
        itemsByPrice.get(price).add(productName);
        
        System.out.println("Product added to catalog!");
    }
    
    public void addItemToCart(Scanner sc) {
        System.out.print("Enter product name: ");
        String productName = sc.nextLine();
        
        if (!productPrices.containsKey(productName)) {
            System.out.println("Product not found in catalog!");
            return;
        }
        
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        
        cartItems.put(productName, cartItems.getOrDefault(productName, 0) + quantity);
        
        System.out.println("Item added to cart!");
    }
    
    public void displayCart() {
        System.out.println("\n=== Shopping Cart (Insertion Order) ===");
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty!");
        } else {
            for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
                String product = entry.getKey();
                int quantity = entry.getValue();
                double price = productPrices.get(product);
                double total = price * quantity;
                
                System.out.println(product + " - Quantity: " + quantity + ", Price: $" + price + ", Total: $" + total);
            }
        }
    }
    
    public void displayProductsByPrice() {
        System.out.println("\n=== Products by Price (Sorted) ===");
        if (itemsByPrice.isEmpty()) {
            System.out.println("No products in catalog!");
        } else {
            for (Map.Entry<Double, List<String>> entry : itemsByPrice.entrySet()) {
                System.out.println("Price $" + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
    
    public void calculateTotal() {
        double total = 0.0;
        
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();
            double price = productPrices.get(product);
            total += price * quantity;
        }
        
        System.out.println("\nCart Total: $" + total);
    }
    
    public void removeItemFromCart(Scanner sc) {
        System.out.print("Enter product name to remove: ");
        String productName = sc.nextLine();
        
        if (cartItems.containsKey(productName)) {
            cartItems.remove(productName);
            System.out.println("Item removed from cart!");
        } else {
            System.out.println("Item not found in cart!");
        }
    }
    
    public void clearCart() {
        cartItems.clear();
        System.out.println("Cart cleared!");
    }
}
