package exercise4_1;

public class Planet {
    private String name;
    private static int planetCount = 0;

    public Planet(String name) {
        this.name = name;
        planetCount++;
    }

    public static int getPlanetCount() {
        return planetCount;
    }

    public static void main(String[] args) {
        Planet earth = new Planet("Earth");
        Planet jupiter = new Planet("Jupiter");
        Planet mars = new Planet("Mars");
        System.out.println(Planet.getPlanetCount());
        System.out.println(earth.getPlanetCount());


    }
}
