interface Worker {
    void performDuties();
}

class Person {
    String name;

    Person(String name, int id) {
        this.name = name;
    }
}

class Chef extends Person implements Worker {
    Chef(String name, int id) {
        super(name, id);
    }

    @Override
    public void performDuties() {
        System.out.println(name + " cooks food.");
    }
}

class Waiter extends Person implements Worker {
    Waiter(String name, int id) {
        super(name, id);
    }

    @Override
    public void performDuties() {
        System.out.println(name + " serves customers.");
    }
}

public class RestaurantManagementHybrid {
    public static void main(String[] args) {
        Worker chef = new Chef("Gordon", 1);
        Worker waiter = new Waiter("Anna", 2);
        chef.performDuties();
        waiter.performDuties();
    }
}
