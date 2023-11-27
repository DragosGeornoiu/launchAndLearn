package ro.dragos.geornoiu.jep.cafe.string.concatenation;

import java.util.List;

public class StringConcatSample {
  public static void main(String[] args) {
    String hello = "hello";
    String world = "world";
    String helloWorld = hello + " " + world;

    // the recommandation was to use a StringBuilder
    StringBuilder sb = new StringBuilder();
    sb.append("hello");
    sb.append(" ");
    sb.append("world");
    String helloWorldTwo = sb.toString();

    // the need to explicitly use StringBuilder is not actual, as nowadays the compiler replaces
    // StringBuilder with String class, you can see even Intelij suggests this
    // for example if we decompile the concat method we will it is using StringBuilder

    // to keep in mind that if you are going to use String concatenation inside a loop, you will
    // need to use a StringBuilder explicitly, as the compiler will create a new instance of
    // StringBuilder per loop, even Intelij suggests that for this case you should use a
    // StringBuilder

    // JEP 280 replaces the bytecode that would have been generated previous for the concat  method
    // to a bytecode that has an InvokeDynamic to a makeConcatWithConstants method of the
    // StringConcat factory, which delegates the concatenation to some other code.
    // Delegating the code to a method which is not part of the bytecode is quite powerful because
    // that code can be  enhanced from one version of the JDK to the next, older code being able to
    // benefit from this without the need to recompile the code.

  }

  private static String concat(String s1, String s2) {
    return s1 + s2;
  }

  private static String concatWithLoop(List<String> strings) {
    String result = "";
    for (String s : strings) {
      result += s;
    }
    return result;
  }
}
