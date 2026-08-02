import java.time.LocalDate;

/**
 * Represents a car lease agreement for a customer.
 * <p>
 * A contract records the customer's details, the vehicle price, the start
 * date, and the length of the lease in years. {@code Contract} itself does
 * not validate the contract length; that validation is performed by
 * {@link RentalSummary}, since only a subset of contract types (those with
 * a supported {@link RentalCalculator} implementation) require a specific
 * length. This keeps {@code Contract} usable for any future contract type
 * without over-constraining it here.
 * <p>
 * Instances are immutable once created.
 */
public class Contract {
    private final String customerName;
    private final Integer customerAge;
    private final LocalDate startDate;
    private final double carPrice;
    private final Integer contractLengthYears;

    /**
     * Creates a new contract.
     *
     * @param customerName        the customer's name
     * @param customerAge         the customer's age
     * @param startDate           the date the contract begins
     * @param contractLengthYears the length of the contract in years
     * @param carPrice            the price of the leased vehicle
     */
    public Contract(String customerName, Integer customerAge, LocalDate startDate, Integer contractLengthYears, double carPrice) {
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.startDate = startDate;
        this.carPrice = carPrice;
        this.contractLengthYears = contractLengthYears;
    }

    /**
     * Returns the customer's name.
     *
     * @return customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Returns the customer's age.
     *
     * @return customer age
     */
    public Integer getCustomerAge() {
        return customerAge;
    }

    /**
     * Returns the date this contract begins.
     *
     * @return contract start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the price of the leased vehicle.
     *
     * @return vehicle price
     */
    public double getCarPrice() {
        return carPrice;
    }

    /**
     * Returns the length of this contract, in years.
     *
     * @return contract length in years
     */
    public Integer getContractLengthYears() {
        return contractLengthYears;
    }
}