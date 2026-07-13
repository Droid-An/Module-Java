package exercise3_1;

public class Dog extends Animal{
    public Dog(String name) {
        super(name);
    }

    public void bark(){
        System.out.println(getName()+" bark");
    }
}
