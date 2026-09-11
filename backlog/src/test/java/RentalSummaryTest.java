import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalSummaryTest {
    @Test
    void constructSummaryWithRightProperties() throws InvalidContractLengthException {
        Contract oneYearContract = new Contract("John Smith", 25, LocalDate.now(), 1, 10000);
        Rental nextRental = new Rental(LocalDate.now().plusMonths(1), 833.3333333333334, 16.666666666666668, false);
        RentalSummary rentalSummary = new RentalSummary(oneYearContract);
        assertThat(rentalSummary.getNextRental().get()).usingRecursiveComparison().isEqualTo(nextRental);
        assertEquals(LocalDate.now().plusYears(1), rentalSummary.getEndDate());
        assertEquals(10000, rentalSummary.getTotalCapital());
        assertEquals(200, rentalSummary.getTotalInterest());
        assertEquals(12, rentalSummary.getRentals().size());
        assertThat(rentalSummary.rentals.get(0)).usingRecursiveComparison().isEqualTo(nextRental);
        assertEquals(12, rentalSummary.numberOfRemainingRentals);
    }

    @Test
    void throwsWhenContractIs2Years() {
        Contract twoYearContract = new Contract("John Smith", 25, LocalDate.now(), 2, 10000);
        assertThatThrownBy(() -> new RentalSummary(twoYearContract)).isInstanceOf(InvalidContractLengthException.class)
                .hasMessageContaining("Contract length isn't 1 or 3");
    }

}