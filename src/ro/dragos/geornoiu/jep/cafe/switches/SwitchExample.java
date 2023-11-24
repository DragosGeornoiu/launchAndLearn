package ro.dragos.geornoiu.jep.cafe.switches;

public class SwitchExample {

  public static void main(String[] args) {
    var day = DaysOfWeek.WEDNESDAY;

    //previous way of writing a switch using switch statements
    switch (day) {
      case MONDAY:
      case TUESDAY:
        System.out.println("school");
        break;
      case WEDNESDAY:
        System.out.println("music and sport");
        break;
      case THURSDAY:
      case FRIDAY:
        System.out.println("more school");
        break;
    }

    //previous way of writing a switch using switch expressions
    switch (day) {
      case MONDAY, TUESDAY ->    System.out.println("school");
      case WEDNESDAY -> System.out.println("music and sport");
      case THURSDAY,FRIDAY -> System.out.println("more school");
    }

    //but it is an expression, you can assign the switch result to a variable
    String activity = switch (day) {
      case MONDAY, TUESDAY ->    "school";
      case WEDNESDAY -> "music and sport";
      case THURSDAY,FRIDAY -> "more school";
    };
    System.out.println(activity);
    // in order to achieve the previous code before the addition of switch expressions ou would have had to move the
    // switch statement to a new method and return the value from that method
    // do not confuse with a lambda expression, they are not related even though the syntax might look alike

  }
}
