package exericse._3_3;

import exercise._3_3.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.BDDAssertions.*;

public class NotificationServiceTest {

    //    verifies that sendWelcome("badaddress.com") throws an exception, checking the type and the content of the message.
    @Test
    public void throwsWhenNoAtSymbol() {
        NotificationService notificationService = new NotificationService();
        then(catchThrowable(() -> notificationService.sendWelcome("badaddress.com")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Email address must contain '@'");

    }

//Take the tests written under NotificationService and parameterise them so that two methods are used to verify behaviours; one for a variety of valid addresses and another for invalid addresses.
//
//In each case provide a suitable name for the test case that includes the parameter value.

    @ParameterizedTest(name = "{0} passes")
    @ValueSource(strings={"goodadress@gmail.com","short@email.io"})
    public void sendWhenEmailIsValid(String validEmail){
        NotificationService notificationService = new NotificationService();
        then(notificationService.sendWelcome(validEmail)).isEqualTo("Welcome "+ validEmail);
    }

    @ParameterizedTest(name = "{0} throws")
    @ValueSource(strings={"goodadressgmail.com","shortemail.io"})
    public void throwWhenEmailIsNotValid(String invalidEmail){
        NotificationService notificationService = new NotificationService();
        then(catchThrowable(() -> notificationService.sendWelcome(invalidEmail)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Email address must contain '@'");
    }



}
