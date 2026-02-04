import java.util.*;

interface Category {
    String name();
}

class BookCategory implements Category {
    private final String n;

    public BookCategory(String n) {
        this.n = n;
    }

    public String name() {
        return n;
    }
}

class ClothingCategory implements Category {
    private final String n;

    public ClothingCategory(String n) {
        this.n = n;
    }

    public String name() {
        return n;
    }
}

class GadgetCategory implements Category {
    private final String n;

    public GadgetCategory(String n) {
        this.n = n;
    }

    public String name() {
        return n;
    }
}

class Product<T extends Category> {
    private final String title;
    private double price;
    private final T category;

    public Product(String title, double price, T category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public T getCategory() {
        return category;
    }
}

class Catalog {
    private final List<Product<?>> products = new ArrayList<>();

    public void add(Product<?> p) {
        products.add(p);
    }

    public List<Product<?>> all() {
        return Collections.unmodifiableList(products);
    }

    public static <P extends Product<?>> void applyDiscount(P product, double percentage) {
        product.setPrice(product.getPrice() * (1 - percentage / 100.0));
    }
}

public class DynamicOnlineMarketplace {
    public static void main(String[] args) {
        Product<BookCategory> book = new Product<>("Algorithms", 50.0, new BookCategory("Books"));
        Product<ClothingCategory> shirt = new Product<>("T-Shirt", 20.0, new ClothingCategory("Clothing"));
        Product<GadgetCategory> earbuds = new Product<>("Earbuds", 35.0, new GadgetCategory("Gadgets"));
        Catalog catalog = new Catalog();
        catalog.add(book);
        catalog.add(shirt);
        catalog.add(earbuds);
        Catalog.applyDiscount(book, 10);
        Catalog.applyDiscount(shirt, 5);
        Catalog.applyDiscount(earbuds, 15);
        for (Product<?> p : catalog.all())
            System.out.println(p.getCategory().name() + ": " + p.getTitle() + " - " + p.getPrice());
    }
}