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
        return Optional.of(new RentalSummary(contract));
    }

    void printRentalSummary(Contract contract) {
        RentalSummaryService rentalSummaryService = new RentalSummaryService();
        System.out.println(rentalSummaryService.generateRentalSummary(contract));
    }

}
