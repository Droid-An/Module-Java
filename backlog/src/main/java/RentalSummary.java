import java.util.List;

public class RentalSummary {
    List<Rental> rentals;
    Contract contract;
    String customerName;
    String customerAge;
    String startDate;
    String endDate;

    public RentalSummary(Contract contract) {
        this.contract = contract;
    }

}

//The system should log the rental summary to the console, printed in a human-readable format.
//The summary information should contain:
//The customer’s name and age
//The dates of the contract i.e. start and end date
//All rentals, sorted by due date
//The next due rental
//The total amount of capital and interest on the contract
//The number of remaining outstanding rentals
//If the contract is complete, i.e. the current date is after the final rental due due, then no summary should be printed.
// Instead the console should print a message to say the contract is completed.