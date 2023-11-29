# Sealed classes

URL: https://www.youtube.com/watch?v=652kheEraHQ&list=PLX8CzqL3ArzV4BpOzLanxd4bZr46x5e87&index=20

JEP 409

Sealed classes cover concrete classes, abstract classes and interfaces.

Goals

* to allow the author of a class or interface to choose which other class is allowed to extend or implement this class
  or interface.
* to offer a syntax that will allow for finer control over the one we have today
* give the possibility to have exhaustive switch of types, which is an upcoming feature.

Before sealed classes, in order to control who can extend your class you can:

* forbid any extension with the final keyword
* play with visibility of constructor (package-protected). Not great as it is not that explicit.

It was explicitly wanted to avoid adding the functionality of friend classes or friend functions in the JDK. A friend
class can be seen in C++, if a class A declared that class B as a friend, it can have access to its private members.

Classes which are involved in the "sealed" relationship have to be either

* the class or interface using the "sealed" keyword must declare the classes or interfaces which extend/implement it.
* the classes or interfaces which extend/implement it have to be either in the same package if using a unnamed module (
  JDK9) or in the same module if they are in a named module.

A class that extends a sealed class can:

* be itself a sealed class
* it ca nbe final explicitly by using the final keyword
* it can be final efectively by for example using records
* it can be non-sealed, which is a new keyword

Using the "non-saeled" keyword means that the class can be freely extended, so the hierarchy is not closed.

Impacts on cast conversion and instanceof:

    interface I {}
    class A {}
    class B extends A implements I {}

    process(A a) {
      //this is accepted by the compiler because there might be thta at runtime there exists a class B
      //however if class A would be final, this would be a compiler error because class B cannot exist, an instance of 
      //can never be an instance of I
      if(a instanceof I) {
      } else {
      }
    }

The same applies for sealed classes

    sealed interface I permis B  {}
    class A {}
    final class B implements I {}

    process(A a) {
      //this would be a compiler error because an instance of A can neber B an instance of I since I permits only B, 
      //which cannot extend A
      if(a instanceof I) {
      } else {
      }
    }

Other mentions

* The JVM will not allow you to create a non-permited implementation of a sealed type at runtime
* 2 methods have been added on the class Class to check if the class is sealed and get the array of permited classes if
  they exist
    