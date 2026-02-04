import java.util.*;

abstract class WarehouseItem {
    private final String name;

    public WarehouseItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Electronics extends WarehouseItem {
    public Electronics(String name) {
        super(name);
    }
}

class Groceries extends WarehouseItem {
    public Groceries(String name) {
        super(name);
    }
}

class Furniture extends WarehouseItem {
    public Furniture(String name) {
        super(name);
    }
}

class Storage<T extends WarehouseItem> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return Collections.unmodifiableList(items);
    }

    public static void display(List<? extends WarehouseItem> list) {
        for (WarehouseItem item : list)
            System.out.println(item.getName());
    }
}

public class SmartWarehouseManagementSystem {
    public static void main(String[] args) {
        Storage<Electronics> electronicsStorage = new Storage<>();
        electronicsStorage.add(new Electronics("Laptop"));
        electronicsStorage.add(new Electronics("Phone"));

        Storage<Groceries> groceriesStorage = new Storage<>();
        groceriesStorage.add(new Groceries("Apples"));
        groceriesStorage.add(new Groceries("Milk"));

        Storage<Furniture> furnitureStorage = new Storage<>();
        furnitureStorage.add(new Furniture("Chair"));
        furnitureStorage.add(new Furniture("Table"));

        List<WarehouseItem> all = new ArrayList<>();
        all.addAll(electronicsStorage.getAll());
        all.addAll(groceriesStorage.getAll());
        all.addAll(furnitureStorage.getAll());
        Storage.display(all);
    }
}