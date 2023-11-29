# Factory Method pattern

The factory method pattern is a creational pattern that uses factory methods to deal with the problem of creating
objects without having to specify the exact class of the object that will be created. This is done by creating objects
by calling a factory method—either specified in an interface and implemented by child classes, or implemented in a base
class and optionally overridden by derived classes—rather than by calling a
constructor. (https://en.wikipedia.org/wiki/Factory_method_pattern)

A different approach is to implement the Factory Method Pattern using default methods.

In Factory Method a class or an interface relies on a derived class to provide the implementation whereas the base class
provides the common behavior.

Factory Method uses inheritance as design tool.