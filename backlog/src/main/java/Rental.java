import java.time.LocalDate;

/**
 * Represents a single monthly rental installment on a lease {@link Contract}.
 * <p>
 * Each rental is made up of a capital portion (the value of the vehicle
 * being paid off) and an interest portion (the cost of leasing), due on a
 * specific date. A rental is considered paid once its due date has passed.
 */
public class Rental {
    final LocalDate dueDate;
    final double capitalAmount;
    final double interestAmount;
    boolean paid;

    /**
     * Creates a rental installment.
     *
     * @param dueDate        the date this rental is due
     * @param capitalAmount  the capital portion of this rental
     * @param interestAmount the interest portion of this rental
     * @param paid           whether this rental has already been paid,
     *                       i.e. whether its due date is on or before today
     */
    public Rental(LocalDate dueDate, double capitalAmount, double interestAmount, boolean paid) {
        this.dueDate = dueDate;
        this.capitalAmount = capitalAmount;
        this.interestAmount = interestAmount;
        this.paid = paid;
    }

    /**
     * Returns whether this rental has already been paid.
     *
     * @return {@code true} if this rental's due date is on or before today,
     *         {@code false} otherwise
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Returns a human-readable, multi-line representation of this rental,
     * including its due date, capital and interest amounts, and paid status.
     *
     * @return formatted string describing this rental
     */
    @Override
    public String toString() {
        return String.format("Due date: %1$tA, %1td %1$tB %1$tY %n" +
                "Capital amount: %f%n" +
                "Interest amount: %f%n" +
                "Paid: %b%n", dueDate, capitalAmount, interestAmount, paid);
    }
}