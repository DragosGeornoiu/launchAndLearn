# Laziness

Laziness should be considered a core principle of functional programming, otherwise when we code in functional
programming, we just make the code look functional or pretty In order to convert a eager evaluated code, we can just
convert it as the following example

    myFunction(Type value) //eager 
    myFunction(Supplier<Type> supplier) // lazy

By accepting the supplier as parameter, even if the function would be called directly, it is lazily evaluated and only
once in the appropriate implementation

David wheeler: In CS we can solve almost any problem by introducing one more level of indirection.

In procedural code, pointers give the power of indirection. In OO code, overriding functions give the power of
indirection. In FP code, lambdas give the power of indirection.

