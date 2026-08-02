import java.time.LocalDate;

public class Rental {
    LocalDate dueDate;
    double capitalAmount;
    double interestAmount;
    boolean paid;

    public Rental(LocalDate dueDate, double capitalAmount, double interestAmount, boolean paid) {
        this.dueDate = dueDate;
        this.capitalAmount = capitalAmount;
        this.interestAmount = interestAmount;
        this.paid = paid;
    }

    public boolean isPaid() {
        return paid;
    }

    @Override
    public String toString() {
        return String.format("Due date: %1$tA, %1td %1$tB %1$tY %n" +
                "Capital amount: %f%n" +
                "Interest amount: %f%n" +
                "Paid: %B%n", dueDate, capitalAmount, interestAmount, paid);
    }
}
