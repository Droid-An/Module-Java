package exercise3_1;

public class Animal {
    private String name;

    public String getName() {
        return name;
    }
    public Animal(String name){
        this.name = name;
    }

    public void makeSound() {
        System.out.println(getName()+ " animal doesn't make any specific sounds");
    }

}



