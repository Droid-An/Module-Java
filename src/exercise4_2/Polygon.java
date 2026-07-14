package exercise4_2;
public final class Polygon {
    //if not initialized, I get an Error: Field 'numberOfSides' might not have been initialized
    private final int numberOfSides;

    public Polygon(int numberOfSides) {
        this.numberOfSides = numberOfSides;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }
//    it won't compile, because "Cannot assign a value to final variable 'numberOfSides'"
    public void setNumberOfSides(int numberOfSides) {
        this.numberOfSides = numberOfSides;
    }

    public final void describe(){
        System.out.println("I am a polygon with " + getNumberOfSides() + " sides");
    }
}
