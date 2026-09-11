import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

/**
 * Generates and prints rental summaries for car lease {@link Contract}s.
 * <p>
 * A rental summary includes the customer's details, the contract's start
 * and end dates, the full list of rentals, the next outstanding rental,
 * total capital and interest, and the number of remaining unpaid rentals.
 * If a contract has already run its full term, a completion message is
 * printed instead of the summary.
 *
 * @see RentalSummary
 * @see Contract
 */
public class RentalSummaryService {
    private static final Logger log = LoggerFactory.getLogger(RentalSummaryService.class);

    /**
     * Demonstrates generating and printing rental summaries for a range of
     * contracts, including a contract with an unsupported length and a
     * contract that has already completed.
     *
     * @param args not used
     */
    public static void main(String[] args) {

        Contract oneYearContract = new Contract("John Smith", 25, LocalDate.of(2025, 8, 12), 1, 10000);
        Contract twoYearContract = new Contract("Michael Jones", 56, LocalDate.of(2025, 12, 12), 2, 20000);
//        Contract threeYearContract = new Contract("Jane Doe", 77, LocalDate.of(2024, 4, 1), 3, 50000);
//        Contract completedContract = new Contract("Rosie Parker", 47, LocalDate.of(2021, 7, 10), 3, 40000);

        RentalSummaryService rentalSummaryService = new RentalSummaryService();
        rentalSummaryService.handleContract(oneYearContract);
        rentalSummaryService.handleContract(twoYearContract); // Should throw a suitable error
//        rentalSummaryService.printRentalSummary(threeYearContract);
//        rentalSummaryService.printRentalSummary(completedContract); // Should print a message to say the contract is complete
    }

    /**
     * Builds a {@link RentalSummary} for the given contract.
     *
     * @param contract the contract to summarize
     * @return an {@link Optional} containing the generated summary; always
     * present, since {@link RentalSummary}'s constructor either
     * succeeds or throws
     */
    private Optional<RentalSummary> generateRentalSummary(Contract contract) throws InvalidContractLengthException {
        return Optional.of(new RentalSummary(contract));
    }

    private void printSummary(Contract contract, RentalSummary rentalSummary) {
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
    }

    /**
     * Prints a rental summary for the given contract to the console.
     * <p>
     * The printed summary contains:
     * <ul>
     *     <li>the customer's name and age</li>
     *     <li>the contract's start and end dates</li>
     *     <li>all rentals for the contract</li>
     *     <li>the next outstanding rental, if any</li>
     *     <li>the total capital and interest for the contract</li>
     *     <li>the number of remaining unpaid rentals</li>
     * </ul>
     * If the contract's end date has already passed, a completion message
     * is printed instead of the full summary.
     * <p>
     * Any error encountered while generating the summary (for example, an
     * unsupported contract length) is caught and logged to the error
     * console rather than propagating to the caller.
     *
     * @param contract the contract to print a summary for
     */
    void handleContract(Contract contract) {
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);
        log.info("Processing contract {}", contract);
        try {
            RentalSummaryService rentalSummaryService = new RentalSummaryService();
            RentalSummary rentalSummary = (rentalSummaryService.generateRentalSummary(contract).orElseThrow());
            rentalSummaryService.printSummary(contract, rentalSummary);
        } catch (RuntimeException | InvalidContractLengthException e) {
            log.error("Error while processing contract", e);
        } finally {
            MDC.clear();
            System.out.printf("--------------------------------------------%n");
        }
    }
}