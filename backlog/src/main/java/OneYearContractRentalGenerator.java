import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OneYearContractRentalGenerator implements RentalCalculator {
    @Override
    public List<Rental> generateRentals(Contract contract) {

        List<Rental> rentals = new ArrayList<>(12);

        double capitalAmount = contract.getCarPrice() / 12;
        double interestAmount = capitalAmount / 100 * 2;
        for (int month = 0; month < rentals.size(); month++) {
            LocalDate dueDate = contract.getStartDate().plusMonths(1);
            rentals.add(new Rental(dueDate, capitalAmount, interestAmount, false));
        }
        return rentals;
    }
}
//        rentals.forEach((rental -> {
//            rental = new Rental(contract.ge)
//        }));