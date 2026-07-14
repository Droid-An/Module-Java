package exercise4_2;

public class Configuration {
    public static final int MAX_USERS = 100;

    private final String systemName;

    public Configuration(String systemName) {
        this.systemName = systemName;
    }
    //Error: Cannot assign a value to final variable 'systemName'
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public static void main(String[] args) {
        //Error: Cannot assign a value to final variable 'MAX_USERS'
        Configuration.MAX_USERS=200;

        Configuration configuration = new Configuration("Name");
        configuration.setSystemName("Another name");

    }
}
