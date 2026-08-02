import java.util.List;

/**
 * Generates the monthly rentals for a lease contract.
 * <p>
 * Monthly capital is the vehicle price divided by amount of months in a contract length; monthly interest is
 * 2% of the vehicle price divided by amount of months in a contract length.
 */
public interface RentalCalculator {
    List<Rental> generateRentals(Contract contract);

}
