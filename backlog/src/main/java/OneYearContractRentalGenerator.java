import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates the 12 monthly rentals for a 1-year lease contract.
 * <p>
 * Monthly capital is the vehicle price divided by 12; monthly interest is
 * 2% of the vehicle price divided by 12.
 */
public class OneYearContractRentalGenerator implements RentalCalculator {

    /**
     * Generates the 12 monthly rentals for the given contract, starting
     * one month after the contract's start date.
     *
     * @param contract the 1-year contract to generate rentals for
     * @return list of 12 rentals, one per month
     */
    @Override
    public List<Rental> generateRentals(Contract contract) {

        List<Rental> rentals = new ArrayList<>(12);

        double capitalAmount = contract.getCarPrice() / 12;
        double interestAmount = (contract.getCarPrice() / 100 * 2) / 12;
        for (int month = 1; month <= 12; month++) {
            LocalDate dueDate = contract.getStartDate().plusMonths(month);
            rentals.add(new Rental(dueDate, capitalAmount, interestAmount, !dueDate.isAfter(LocalDate.now())));
        }
        return rentals;
    }
}
