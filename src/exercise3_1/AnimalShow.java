package exercise3_1;

public class AnimalShow {

    public void perform(Animal animal){
        if (animal instanceof Dog){ //instance of
            Dog dog = (Dog) animal; //down casting
            dog.bark();
        } else if (animal instanceof Cat) {
            Cat cat = (Cat) animal;
            cat.meow();
        }
        else {
            System.out.println("default message");
        }
    }

    public static void main(String[] args) {
        Cat cat = new Cat("Cat");
        Dog dog = new Dog("Dog");
        Animal animal = new Animal("animal");
        AnimalShow animalShow = new AnimalShow();
        animalShow.perform(cat);
        animalShow.perform(dog);
        animalShow.perform(animal);
    }
}
