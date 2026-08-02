import java.time.LocalDate;

public class Contract {
    String customerName;
    Integer customerAge;
    LocalDate startDate;
    double carPrice;
    Integer contractLengthYears;

    public Contract(String customerName, Integer customerAge, LocalDate startDate, Integer contractLengthYears, double carPrice) {
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.startDate = startDate;
        this.carPrice = carPrice;
        this.contractLengthYears = contractLengthYears;
    }


    public String getCustomerName() {
        return customerName;
    }

    public Integer getCustomerAge() {
        return customerAge;
    }

    public LocalDate getStartDate() {
        return startDate;
    }


    public double getCarPrice() {
        return carPrice;
    }

    public Integer getContractLengthYears() {
        return contractLengthYears;
    }
}
