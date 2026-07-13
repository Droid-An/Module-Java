package exercise3_2;

public class PaymentMethod {

    public void pay(double amount) {
        System.out.println("Paying " + amount);
    }

    public void pay(double amount, String currency) {
        System.out.println("Paying " + amount + " " + currency);
    }

    public static void main(String[] args) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.pay(20);
        paymentMethod.pay(20, "dollar");
    }
}
