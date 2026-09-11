import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OneYearContractRentalGeneratorTest {

    @Test
    void generateRentalsCorrectly() {
        Contract oneYearContract = new Contract("John Smith", 25, LocalDate.of(2025, 8, 12), 1, 10000);
        OneYearContractRentalGenerator oneYearContractRentalGenerator = new OneYearContractRentalGenerator();
        List<Rental> rentals = oneYearContractRentalGenerator.generateRentals(oneYearContract);
        assertEquals(12, rentals.size());

        for (Rental r : rentals) {
            boolean expectedPaid = !r.dueDate.isAfter(LocalDate.now());
            assertEquals(r.isPaid(), expectedPaid);
        }
        assertEquals(833.3333333333334, rentals.get(0).capitalAmount);
        assertEquals(16.666666666666668, rentals.get(11).interestAmount);
        assertEquals(oneYearContract.getStartDate().plusMonths(12), rentals.get(11).dueDate);
    }

    @Test
    void marksRentalsAsPaidIfDueDateIsNotAfterToday() {
        Contract oneYearContract = new Contract("John Smith", 25, LocalDate.now().minusMonths(4), 1, 10000);
        OneYearContractRentalGenerator oneYearContractRentalGenerator = new OneYearContractRentalGenerator();
        List<Rental> rentals = oneYearContractRentalGenerator.generateRentals(oneYearContract);
        assertTrue(rentals.get(3).isPaid());
        assertFalse( rentals.get(5).isPaid());
    }
}