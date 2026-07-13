package exercise2_1;

class BankAccount {
    private double balance;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdraw amount can't be less than 0");
        }
        double newBalance = getBalance() - amount;
        if (newBalance < 0) {
            throw new IllegalArgumentException("Balance can't be less than 0");
        }
        setBalance(newBalance);
    }

    public void deposit(double amount) {
        double newBalance = getBalance() - amount;
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount can't go less than 0");
        }

        setBalance(newBalance);
    }
}

class BankService {
    void withdraw(BankAccount account, double amount) {

        account.withdraw(amount);
    }

    void deposit(BankAccount account, double amount) {
        account.deposit(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.setBalance(100);
        BankService service = new BankService();
        service.withdraw(account, 150);
        System.out.println("Balance: " + account.getBalance());
    }
}