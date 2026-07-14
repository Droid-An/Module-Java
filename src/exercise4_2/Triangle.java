package exercise4_2;

import exercise4_3.Polygon;

// when Polygon is a final class, I see this error: "Cannot inherit from final class 'exercise4_3.Polygon'"
public class Triangle extends Polygon {

    public Triangle(int numberOfSides) {
        super(numberOfSides);
    }

//    'describe()' cannot override 'describe()' in 'exercise4_3.Polygon'; overridden method is final
    @Override
    public void describe(){

    }
}
