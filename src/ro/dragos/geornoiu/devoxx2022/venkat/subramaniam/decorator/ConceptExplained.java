package ro.dragos.geornoiu.devoxx2022.venkat.subramaniam.decorator;

import java.util.function.Function;

public class ConceptExplained {
  public static void print(int number, String message, Function<Integer, Integer> func) {
    System.out.println(number + " " + message + ": " + func.apply(number));
  }

  public static void main(String[] args) {
    Function<Integer, Integer> inc = value -> value + 1;
    print(5, "incremented", inc);
    print(6, "incremented", inc);

    Function<Integer, Integer> doubled = value -> value * 2;
    print(5, "doubled", doubled);

    print(5, "incremented and doubled", value -> (value + 1) * 2);
    // if we look at the last print call, it is composed of the two previous functions
    // since we cannot pass 2 functions to print, we have to find a way to compose the two functions

    print(5, "incremented and doubled", inc.andThen(doubled));
    // this is how we combine functions together
  }
}
