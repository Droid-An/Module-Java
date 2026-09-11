import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RentalSummaryServiceTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void redirectStdOut() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStdOut() {
        System.setOut(originalOut);
    }

    @Test
    void itPrintsOutThatContractIsCompleteWhenItsComplete() {
        RentalSummaryService rentalSummaryService = new RentalSummaryService();
        Contract oneYearContract = new Contract("John Smith", 25, LocalDate.now().minusMonths(14), 1, 10000);
        rentalSummaryService.handleContract(oneYearContract);
        String printed = outContent.toString();
        assertThat(printed).contains("Contract is completed");
    }

    @Test
    void writeCorrectAmountOfRentals() {
        Contract oneYearContract = new Contract("John Smith", 25, LocalDate.now(), 1, 10000);
        RentalSummaryService rentalSummaryService = new RentalSummaryService();
        rentalSummaryService.handleContract(oneYearContract);
        String printed = outContent.toString();
        assertThat(printed).contains("Rental summary for John Smith, 25");
        assertThat(printed).contains("Total amount of capital");
        assertThat(printed).doesNotContain("Contract is completed");
        long dueLines = printed.lines().filter(line -> line.startsWith("Due date:")).count();
        assertThat(dueLines).isEqualTo(12);
    }
}