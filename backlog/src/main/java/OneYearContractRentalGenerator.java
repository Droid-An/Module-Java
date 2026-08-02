import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OneYearContractRentalGenerator implements RentalCalculator {
    @Override
    public List<Rental> generateRentals(Contract contract) {

        List<Rental> rentals = new ArrayList<>(12);

        double capitalAmount = contract.getCarPrice() / 12;
        double interestAmount = capitalAmount / 100 * 2;
        for (int month = 1; month <= 12; month++) {
            LocalDate dueDate = contract.getStartDate().plusMonths(month);
            rentals.add(new Rental(dueDate, capitalAmount, interestAmount, dueDate.isBefore(LocalDate.now().plusDays(1))));
        }
        return rentals;
    }
}
