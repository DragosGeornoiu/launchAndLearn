package ro.dragos.geornoiu.devoxx2022.venkat.subramaniam.layzness;

import java.util.function.Supplier;

class Lazy<T> {
  private T instance;
  private Supplier<T> supplier;

  public Lazy(Supplier<T> supplier) {
    this.supplier = supplier;
  }

  public T get() {
    if (instance == null) {
      instance = supplier.get();
    }

    return instance;
  }
}

public class Sample {
  public static int compute(int number) {
    // imagine it takes some time to compute this
    System.out.println("compute method is called");
    return number * 100;
  }

  public static void main(String[] args) {
    //    int value = 4;

    // in this case, because we have an && between the operations, the first part of the if is
    // evaluated and the compute method is never called
    // this is called short circuiting
    //    if (value > 4 && compute(value) > 100) {
    //      System.out.println("path 1");
    //    } else {
    //      System.out.println("path 2");
    //    }

    // because the method is called explicitly outside the if, now the compute method will actually
    // be called
    //    int temp = compute(value);
    //    if (value > 4 && temp > 100) {
    //      System.out.println("path 1");
    //    } else {
    //      System.out.println("path 2");
    //    }

    // using Lazy class
    int value = 4;
    Lazy<Integer> temp = new Lazy<>(() -> compute(value));
    if (value > 4 && temp.get() > 100) {
      System.out.println("path 1");
    } else {
      System.out.println("path 2");
    }
  }
}
