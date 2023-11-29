package ro.dragos.geornoiu.devoxx2022.venkat.subramaniam.fluent.interfaces;

import java.util.function.Consumer;

class Mailer {
  private Mailer() {}
  ;

  public Mailer from(String addr) {
    System.out.println("from...");
    return this;
  }

  public Mailer to(String adds) {
    System.out.println("to...");
    return this;
  }

  public Mailer subject(String subj) {
    System.out.println("subject...");
    return this;
  }

  public Mailer body(String body) {
    System.out.println("body...");
    return this;
  }

  public void send() {
    System.out.println("sending...");
  }

  // we can also modify the send method to use a Consumer
  public static void send(Consumer<Mailer> consumer) {
    var mailer = new Mailer();
    consumer.accept(mailer);
    System.out.println("sending...");
  }
}

public class Sample {
  public static void main(String[] args) {
    // when each of the method has a void return type we need to call each one in the following way
    //    Mailer mailer = new Mailer();
    //    mailer.from("test@test.com");
    //    mailer.to("test1@test.com");
    //    mailer.subject("test subj");
    //    mailer.body("test body");
    //    mailer.send();

    System.out.println("-------------");
    // if we implement the fluent interfaces or cascasind metod pattern, by returning the object in
    // each method, we can now call them in the following way, which is more prettier:
    //    new Mailer()
    //        .from("test@test.com")
    //        .to("test1@test.com")
    //        .subject("test subj")
    //        .body("test body")
    //        .send();

    System.out.println("-------------");
    // after making the send method static and adding a Consumer parameter for it we can call the
    // code in the following way
    // what we also did at this point is to make te constructor private, allowing the instantiation
    // only via the send method
    Mailer.send(
        mailerLocal ->
            mailerLocal
                .from("test@test.com")
                .to("test1@test.com")
                .subject("test subj")
                .body("test body"));
    // pretty nice that we have now not allowed the creation of objects unless they are actually
    // used in the purpose we want them, meaning for the Mailer case you cannot just create a Mailer
    // object without actually sending it also.
  }
}
