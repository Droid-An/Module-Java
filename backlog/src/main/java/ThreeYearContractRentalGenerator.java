import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ThreeYearContractRentalGenerator implements RentalCalculator {

    @Override
    public List<Rental> generateRentals(Contract contract) {

        List<Rental> rentals = new ArrayList<>(36);

        double capitalAmount = contract.getCarPrice() / 36;
        double interestAmount = capitalAmount / 100 * 3;
        for (int month = 0; month < rentals.size(); month++) {
            LocalDate dueDate = contract.getStartDate().plusMonths(1);
            rentals.add(new Rental(dueDate, capitalAmount, interestAmount, false));
        }
        return rentals;
    }
}
