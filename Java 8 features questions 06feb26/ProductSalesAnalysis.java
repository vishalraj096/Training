import java.util.*;
import java.util.stream.Collectors;

class Sale {
    private String productId;
    private int quantity;
    private double price;

    public Sale(String productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}

class ProductSales {
    private String productId;
    private double totalRevenue;

    public ProductSales(String productId, double totalRevenue) {
        this.productId = productId;
        this.totalRevenue = totalRevenue;
    }

    public String getProductId() {
        return productId;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    @Override
    public String toString() {
        return "ProductSales{" +
                "productId='" + productId + '\'' +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}

public class ProductSalesAnalysis {

    public static List<ProductSales> analyzeSales(List<Sale> sales) {
        Map<String, Double> revenueByProduct = sales.stream()
                .filter(s -> s.getQuantity() > 10)
                .collect(Collectors.groupingBy(Sale::getProductId,
                        Collectors.summingDouble(s -> s.getQuantity() * s.getPrice())));

        return revenueByProduct.entrySet().stream()
                .map(e -> new ProductSales(e.getKey(), e.getValue()))
                .sorted(Comparator.comparingDouble(ProductSales::getTotalRevenue).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Sale> sales = new ArrayList<>();
        sales.add(new Sale("P1", 5, 100));
        sales.add(new Sale("P1", 20, 100));
        sales.add(new Sale("P2", 15, 200));
        sales.add(new Sale("P3", 30, 50));
        sales.add(new Sale("P4", 12, 500));
        sales.add(new Sale("P5", 8, 1000));
        sales.add(new Sale("P6", 25, 80));

        List<ProductSales> topProducts = analyzeSales(sales);

        System.out.println("Top 5 products by revenue:");
        topProducts.forEach(System.out::println);
    }
}
