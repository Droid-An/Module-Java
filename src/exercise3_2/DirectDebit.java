package exercise3_2;

public class DirectDebit extends PaymentMethod {
    public void pay(double amount) {
        System.out.println("Transferring " + amount);
    }

    public static void main(String[] args) {
        DirectDebit directDebit = new DirectDebit();
        directDebit.pay(20);
        directDebit.pay(20, "dollars");
    }
}
