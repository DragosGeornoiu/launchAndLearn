package ro.dragos.geornoiu.devoxx2022.venkat.subramaniam.arount.method;

import java.util.function.Consumer;

class Resource {
  public Resource() {
    System.out.println("created...");
  }

  public Resource op1() {
    System.out.println("op1...");
    return this;
  }

  public Resource op2() {
    System.out.println("op1...");
    return this;
  }

  // this finalize was a really bad idea, that is why it was also deprecated and the reasoning can
  // be read there.
  // one of the problem is that we never have the guarantee that it is called, even when calling
  // system.gc explicitly.
  public void finalize() {
    System.out.println("Releasing resource...");
  }

  // so we end up implementing our own close method
  public void close() {
    System.out.println("Releasing resource...");
  }
}

class AutoClosableResource implements AutoCloseable {
  public AutoClosableResource() {
    System.out.println("AutoClosable created...");
  }

  public AutoClosableResource op1() {
    System.out.println("AutoClosable op1...");
    return this;
  }

  public AutoClosableResource op2() {
    System.out.println("AutoClosable op1...");
    return this;
  }

  @Override
  public void close() {
    System.out.println("AutoClosable Releasing resource...");
  }
}

class AroundResource {
  public AroundResource() {
    System.out.println("AroundResource created...");
  }

  public AroundResource op1() {
    System.out.println("AutoClosable op1...");
    return this;
  }

  public AroundResource op2() {
    System.out.println("AutoClosable op1...");
    return this;
  }

  private void close() {
    System.out.println("AutoClosable Releasing resource...");
  }

  public static void use(Consumer<AroundResource> consumer) {
    AroundResource resource = new AroundResource(); //before
    try {
      consumer.accept(resource);
    } finally {
      resource.close(); //after
    }
  }
}

public class Sample {
  public static void main(String[] args) {
    Resource resource = new Resource();
    resource.op1();
    resource.op2();
    System.gc(); // also not the best intended method to hope for a garbage collection, but we see
    // finalize is still not called.
    // we implemented our own close method
    // if we implemented and called the close method directly we just end up moving the issue
    resource.close();

    // what if we had an exception? then we remember we can wrap the code in a try-catch
    Resource secondResource = new Resource();
    try {
      secondResource.op1();
      secondResource.op2();
    } finally {
      secondResource.close();
    }
    // but the previous try-finally code is quite verbose and very easy to forget to add try and
    // finally

    // then we remembered automatic resource management or most commonly known as try-with-resources
    // and this fits exactly what we did, so we would refactor our class to implement AutoClosable,
    // but for this example I just created a new class
    try (AutoClosableResource autoClosableResource = new AutoClosableResource()) {
      autoClosableResource.op1();
      autoClosableResource.op2();
    }
    // the issue with this code is that even though less verbose, it still is very error-prone,
    // someone could just forget to use the try with resources/

    // We can talk about how AutoClosable functionality could of been better implemented, but if you
    // would have to create a class which implements AutoClosable, know there are other ways to
    // achieve better the same goal:
    AroundResource.use(
        aroundResource -> {
          aroundResource.op1();
          aroundResource.op1();
        });
    // this code is not verbose and we control both the instantiation of the resource and also
    // ensure that the close method is always called
    // The same can be used when workin with transactions by doings something along the lines of:
    // Transaction.runInTrasaction(tx -> ...);
    // you can think of this usage as an aroung method, it has a before and after part
  }
}
