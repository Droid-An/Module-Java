package exercise._2_2;

public class OrderProcessor {

    public static void main(String[] args) {
        Order order = new Order(123);
        process(order);
    }

    public static void process(Order order) {
        System.out.println("Customer name: " + order.customer.getName().toUpperCase());
    }
}

class Order {
    private final int id;
    final Customer customer;

    public Order(int id) {
        this.id = id;
        this.customer = new Customer("Alice");
    }
}

class Customer {
   private final String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}