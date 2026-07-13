package exercise3_2;

class DirectDebit extends PaymentMethod {
    void pay(double amount) {
        System.out.println("Transferring " + amount);
    }

    public static void main(String[] args) {
        DirectDebit directDebit = new DirectDebit();
        directDebit.pay(20);
        directDebit.pay(20, "dollars");
    }
}
