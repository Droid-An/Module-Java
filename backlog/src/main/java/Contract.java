import java.security.InvalidParameterException;
import java.time.LocalDate;

public class Contract {
    String customerName;
    Integer customerAge;
    LocalDate startDate;
    double carPrice;
    Integer contractLengthYears;

    public Contract(String customerName, Integer customerAge, LocalDate startDate, Integer contractLengthYears, double carPrice) {
        if (!validContractLength(contractLengthYears)) {
            throw new InvalidParameterException("Contract must be 1 or 3 years");
        }
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.startDate = startDate;
        this.carPrice = carPrice;
        this.contractLengthYears = contractLengthYears;
    }

    public boolean validContractLength(int contractLengthYears) {
        return contractLengthYears == 1 || contractLengthYears == 3;

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(Integer customerAge) {
        this.customerAge = customerAge;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }

    public Integer getContractLengthYears() {
        return contractLengthYears;
    }

    public void setContractLengthYears(Integer contractLengthYears) {
        this.contractLengthYears = contractLengthYears;
    }
}
