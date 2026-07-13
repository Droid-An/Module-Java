package exercise3_1;

public class Cat extends Animal{
    public Cat(String name) {
        super(name);
    }

    public void meow(){
        System.out.println(getName()+" meow");
    }
}
