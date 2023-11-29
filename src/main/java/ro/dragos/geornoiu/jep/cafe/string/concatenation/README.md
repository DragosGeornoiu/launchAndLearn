# String Concatenation

URL: https://www.youtube.com/watch?v=w56RUzBLaRE&list=PLX8CzqL3ArzV4BpOzLanxd4bZr46x5e87&index=15

JEP 280

What does indify mean? It comes from the bytecode instructions "invokedynamic", also know as "indy", thus "indify". It
was added in JDK7 and used in JDK8 to implement lambda expressions among other things. This instruction is used in many
places including String concatenation.

JEP 280 was delivered in JDK9, being one of the many benefits of upgrading from 8 to 11 or to 17.

String concatenations are much faster in JDK 17 when comparing to JDK 8 and many types of concatenations have been
adapted:
    Integer.toString()
    "" + int 
    "" + integer
    
Even formatting strings performs better:
    String.format("%ty-%tm-%td", data, ...)
    String.format("%d %d %d, Math.PI...)
    String.format("%+7.4 %+7.4f %+7.4f %+7.4f %+7.4f %+7.4f, Math.PI, ....)
    String.format("%s %s %s", "one"..) //best performance gain from JDK8 to JDK17 is when replacing Strings

