import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Represents a computed summary of a car rental {@link Contract}, including
 * the full schedule of {@link Rental} installments, the next unpaid rental,
 * and aggregate totals for capital and interest.
 * <p>
 * The rental schedule is generated based on the contract's length: a 1-year
 * contract uses {@link OneYearContractRentalGenerator}, a 3-year contract
 * uses {@link ThreeYearContractRentalGenerator}. Any other contract length
 * is not supported and results in an exception.
 */
public class RentalSummary {
    private static final Logger log = LoggerFactory.getLogger(RentalSummary.class);

    final List<Rental> rentals;
    final LocalDate endDate;
    final Optional<Rental> nextRental;
    final double totalCapital;
    final double totalInterest;
    final long numberOfRemainingRentals;

    /**
     * Builds a rental summary for the given contract, generating the full
     * rental schedule and computing totals and the next outstanding rental.
     *
     * @param contract the contract to summarize; must have a supported
     *                 contract length (1 or 3 years)
     * @throws InvalidParameterException if the contract length is neither
     *                                   1 nor 3 years
     */
    public RentalSummary(Contract contract) throws InvalidContractLengthException {
        if (contract.getContractLengthYears() == 1) {
            OneYearContractRentalGenerator oneYearContractRentalGenerator = new OneYearContractRentalGenerator();
            this.rentals = oneYearContractRentalGenerator.generateRentals(contract);
        } else if (contract.getContractLengthYears() == 3) {
            ThreeYearContractRentalGenerator threeYearContractRentalGenerator = new ThreeYearContractRentalGenerator();
            this.rentals = threeYearContractRentalGenerator.generateRentals(contract);
        } else {
            log.error("Contract length {} isn't equal 1 or 3", contract.getContractLengthYears());
            throw new InvalidContractLengthException("Contract length isn't 1 or 3");
        }
        this.endDate = contract.getStartDate().plusYears(contract.getContractLengthYears());
        this.nextRental = rentals.stream()
                .filter(rental -> !rental.isPaid()).findFirst();
        this.totalCapital = contract.getCarPrice();
        this.totalInterest = contract.getCarPrice() / 100 * 2;
        this.numberOfRemainingRentals = calculateNumberOfRetainingRentals(rentals);
    }

    public long calculateNumberOfRetainingRentals(List<Rental> rentals) {
        return rentals.stream()
                .filter(rental -> !rental.isPaid()).count();
    }

    /**
     * Returns the full list of rentals generated for this contract, covering
     * the entire contract duration.
     *
     * @return list of all rentals for the contract
     */
    public List<Rental> getRentals() {
        return rentals;
    }

    /**
     * Returns the date the contract ends, calculated as the contract's
     * start date plus its length in years.
     *
     * @return the contract end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Returns the next rental that has not yet been paid, if any.
     *
     * @return an {@link Optional} containing the next unpaid rental, or
     * empty if all rentals have been paid
     */
    public Optional<Rental> getNextRental() {
        return nextRental;
    }

    /**
     * Returns the total capital amount for the contract, based on the
     * car's price.
     *
     * @return total capital amount
     */
    public double getTotalCapital() {
        return totalCapital;
    }

    /**
     * Returns the total interest amount for the contract, calculated as
     * 2% of the car's price.
     *
     * @return total interest amount
     */
    public double getTotalInterest() {
        return totalInterest;
    }

    /**
     * Returns the number of rentals that have not yet been paid.
     *
     * @return count of remaining (unpaid) rentals
     */
    public long getNumberOfRemainingRentals() {
        return numberOfRemainingRentals;
    }
}