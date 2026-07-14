package exercise4_2;

// when Polygon is a final class, I see this error: "Cannot inherit from final class 'exercise4_2.Polygon'"
public class Triangle extends Polygon {

    public Triangle(int numberOfSides) {
        super(numberOfSides);
    }

//    'describe()' cannot override 'describe()' in 'exercise4_2.Polygon'; overridden method is final
    @Override
    public void describe(){

    }
}
