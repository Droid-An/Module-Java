package exercise3_2;

class PaymentMethod {
    void pay(double amount) {
        System.out.println("Paying " + amount);
    }
    void pay(double amount, String currency){
        System.out.println("Paying " + amount + " " + currency);
    }

    public static void main(String[] args) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.pay(20);
        paymentMethod.pay(20, "dollar");
    }
}
