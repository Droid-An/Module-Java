package exercise3_1;

public class AnimalShow {

    public void perform(Animal animal){
        animal.makeSound();
    }

    public static void main(String[] args) {
        AnimalShow show = new AnimalShow();
        show.perform(new Cat("Cat"));
        show.perform(new Dog("Dog"));
        show.perform(new Animal("animal"));
        show.perform(new Parrot("Polly"));
    }
}
