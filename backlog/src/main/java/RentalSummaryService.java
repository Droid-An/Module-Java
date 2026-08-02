import java.time.LocalDate;
import java.util.Optional;

public class RentalSummaryService {
    public static void main(String[] args) {

        Contract oneYearContract = new Contract("John Smith", 25, LocalDate.of(2025, 8, 12), 1, 10000);
        Contract twoYearContract = new Contract("Michael Jones", 56, LocalDate.of(2025, 12, 12), 2, 20000);
        Contract threeYearContract = new Contract("Jane Doe", 77, LocalDate.of(2024, 4, 1), 3, 50000);
        Contract completedContract = new Contract("Rosie Parker", 47, LocalDate.of(2021, 7, 10), 3, 40000);

        RentalSummaryService rentalSummaryService = new RentalSummaryService();
        rentalSummaryService.printRentalSummary(oneYearContract);
        rentalSummaryService.printRentalSummary(twoYearContract); // Should throw a suitable error
        rentalSummaryService.printRentalSummary(threeYearContract);
        rentalSummaryService.printRentalSummary(completedContract); // Should print a message to say the contract is complete
    }

    private Optional<RentalSummary> generateRentalSummary(Contract contract) {
        return Optional.ofNullable(new RentalSummary(contract));
    }

    void printRentalSummary(Contract contract) {
        try {
            RentalSummaryService rentalSummaryService = new RentalSummaryService();
            RentalSummary rentalSummary = (rentalSummaryService.generateRentalSummary(contract).orElseThrow());
            System.out.printf("%n--------------------------------------------%n");
            if (rentalSummary.getEndDate().isBefore(LocalDate.now())) {
                System.out.println("Contract is completed");
            } else {
                System.out.printf("Rental summary for %s, %d%n", contract.getCustomerName(), contract.getCustomerAge());
                System.out.printf("Contract: %1$tA, %1td %1$tB %1$tY - %2$tA, %1td %2$tB %2$tY%n", contract.getStartDate(), rentalSummary.getEndDate());
                System.out.printf("All rentals:%n");
                rentalSummary.getRentals().forEach(rental -> System.out.println(rental.toString()));
                rentalSummary.getNextRental().ifPresentOrElse(
                        nextRental -> System.out.printf("Next rental %s%n", nextRental),
                        () -> System.out.println("No upcoming rentals — contract complete.")
                );
                System.out.printf("Total amount of capital %f%n", rentalSummary.getTotalCapital());
                System.out.printf("Total interest %f%n", rentalSummary.getTotalInterest());
                System.out.printf("Remaining rentals %d%n", rentalSummary.getNumberOfRemainingRentals());
            }
        } catch (Exception e){
            System.err.println(e);
        }
    }
}
