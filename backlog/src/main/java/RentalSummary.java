import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RentalSummary {
    List<Rental> rentals;
    LocalDate endDate;
    Optional<Rental> nextRental;
    double totalCapital;
    double totalInterest;
    long numberOfRemainingRentals;


    public RentalSummary(Contract contract) {
        this.endDate = contract.startDate.plusYears(contract.getContractLengthYears());
        if (contract.getContractLengthYears() == 1) {
            OneYearContractRentalGenerator oneYearContractRentalGenerator = new OneYearContractRentalGenerator();
            this.rentals = oneYearContractRentalGenerator.generateRentals(contract);
        } else if (contract.getContractLengthYears() == 3) {
            ThreeYearContractRentalGenerator threeYearContractRentalGenerator = new ThreeYearContractRentalGenerator();
            this.rentals = threeYearContractRentalGenerator.generateRentals(contract);
        } else {
            throw new InvalidParameterException("Contract length isn't 1 or 3");
        }
        this.nextRental = rentals.stream()
                .filter(rental -> !rental.isPaid()).findFirst();
        this.totalCapital = contract.getCarPrice();
        this.totalInterest = contract.getCarPrice() / 100 * 2;
        this.numberOfRemainingRentals = rentals.stream()
                .filter(rental -> !rental.isPaid()).count();
    }

    public List<Rental> getRentals() {
        return rentals;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public Optional<Rental> getNextRental() {
        return nextRental;
    }

    public double getTotalCapital() {
        return totalCapital;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    public long getNumberOfRemainingRentals() {
        return numberOfRemainingRentals;
    }
}