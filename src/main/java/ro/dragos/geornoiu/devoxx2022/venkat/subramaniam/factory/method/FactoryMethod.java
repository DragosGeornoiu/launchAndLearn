package ro.dragos.geornoiu.devoxx2022.venkat.subramaniam.factory.method;

interface Pet {}

class Dog implements Pet {}

class Cat implements Pet {}

interface Person {
  // by leaving this to be implemented by each child class, we manage to achieve the Factory Pattern
  Pet getPet();

  default void play() {
    System.out.println("playing with " + getPet());
  }
}

class DogPerson implements Person {
  private Dog dog = new Dog();

  @Override
  public Pet getPet() {
    return dog;
  }
}

class CatPerson implements Person {
  private Cat cat = new Cat();

  @Override
  public Pet getPet() {
    return cat;
  }
}

public class FactoryMethod {

  public static void call(Person person) {
    person.play();
  }

  public static void main(String[] args) {
    call(new DogPerson());
    call(new CatPerson());
  }
}
