import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates the 36 monthly rentals for a 3-year lease contract.
 * <p>
 * Monthly capital is the vehicle price divided by 36; monthly interest is
 * 2% of the vehicle price divided by 36.
 */
public class ThreeYearContractRentalGenerator implements RentalCalculator {

    /**
     * Generates the 36 monthly rentals for the given contract, starting
     * one month after the contract's start date.
     *
     * @param contract the 3-year contract to generate rentals for
     * @return list of 36 rentals, one per month
     */
    @Override
    public List<Rental> generateRentals(Contract contract) {

        List<Rental> rentals = new ArrayList<>(36);


        double capitalAmount = contract.getCarPrice() / 36;
        double interestAmount = (contract.getCarPrice() / 100 * 2) / 36;
        for (int month = 1; month <= 36; month++) {
            LocalDate dueDate = contract.getStartDate().plusMonths(month);

            rentals.add(new Rental(dueDate, capitalAmount, interestAmount, !dueDate.isAfter(LocalDate.now())));
        }
        return rentals;
    }
}
