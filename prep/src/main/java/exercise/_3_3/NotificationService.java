package exercise._3_3;

public class NotificationService {

    public String sendWelcome(String emailAddress) throws IllegalStateException {
        if (!emailAddress.contains("@")) {
            throw new IllegalStateException("Email address must contain '@'");
        }
        return ("Welcome " + emailAddress);

    }
}
