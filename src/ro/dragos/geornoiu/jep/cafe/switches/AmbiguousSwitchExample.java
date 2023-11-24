package ro.dragos.geornoiu.jep.cafe.switches;

public class AmbiguousSwitchExample {
  public static String convertToActivity(DaysOfWeek day) {
    String activity = switch (day) {
      case MONDAY, TUESDAY ->    {
        System.out.println("School");
//        return "school"; //this is a compile time error because it would be really ambiguous to allow this syntax
//        as we do not know if it returns from the case or from the method
        yield "school"; //the 'yield' keyword was added to resolve this ambiguity. It is not a reserved keyword
      }
      default -> "other";
    };

    return activity;
  }
  public static void main(String[] args) {
    var day = DaysOfWeek.MONDAY;
    convertToActivity(day);
  }
}
