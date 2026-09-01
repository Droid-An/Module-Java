package exercise._3_1;

public class PriceService {
    private final ExchangeRateClient client;

    public PriceService(ExchangeRateClient client) {
        this.client = client;
    }

    public double convertTo(double eurPrice, String targetCurrency) {
        return eurPrice * client.getRate("EUR", targetCurrency);
    }

    public double convertToGbp(double eurPrice) {
        return eurPrice * client.getRate("EUR", "GBP");
    }
}

