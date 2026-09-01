package exericse._3_1;
import exercise._3_1.ExchangeRateClient;
import exercise._3_1.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.invocation.InvocationOnMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

public class PriceServiceTest {
//    Provide a fixed exchange rate regardless of what is requested
    @Test
    public void fixedRateMock() {
        //I couldn't make annotations work, so I did as usual
        ExchangeRateClient rateClient = mock(ExchangeRateClient.class);
        given(rateClient.getRate(anyString(), anyString())).willReturn(1.1);
        PriceService priceService = new PriceService(rateClient);
        assertEquals(24.42,priceService.convertTo(22.2, "GBP"));
    }

//    Add a method convertToGbp and amend the mock to provide different exchange rates for EUR->GBP and EUR->USD
    @Test
    public void differentExchangeForDifferentRates() {
        ExchangeRateClient rateClient = mock(ExchangeRateClient.class);
        given(rateClient.getRate(anyString(), anyString())).will((InvocationOnMock invocation) ->
                invocation.getArgument(1).equals("GBP")
                        ? 1.1
                        : 0.9);
        PriceService priceService = new PriceService(rateClient);
        assertEquals(24.42,priceService.convertTo(22.2, "GBP"));
        assertEquals(19.98,priceService.convertTo(22.2, "USD"));
    }

//    Take the tests written under PriceService and parameterise them so that each currency is called from a single test method
    @ParameterizedTest(name ="converts {0} correctly")
    @ValueSource(strings={"GPB","USD"})
    public void paramTest(String argument){
        ExchangeRateClient rateClient = mock(ExchangeRateClient.class);
        given(rateClient.getRate(anyString(), anyString())).will((InvocationOnMock invocation) ->
                invocation.getArgument(1).equals("GBP")
                        ? 1.1
                        : 0.9);
        PriceService priceService = new PriceService(rateClient);
        if (argument.equals("GBP")){
            assertEquals(24.42,priceService.convertTo(22.2, "GBP"));
        } else if (argument.equals("USD")) {
            assertEquals(19.98,priceService.convertTo(22.2, "USD"));
        }

    }

}
