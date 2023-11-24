# Switch expressions

URL: https://www.youtube.com/watch?v=652kheEraHQ&list=PLX8CzqL3ArzV4BpOzLanxd4bZr46x5e87&index=20

JEP 361

Up until JEP 361 we had "switch statements" in Java and what this JEP brings are "switch expressions".

Before switch expressions, there was a limited set of valid types that could of been used:

* byte, short, char or int primitives and their wrapper classes
* enumerated types
* String

Please see SwitchExample.java in order to see how the switch expressions are written.

Please see the AmbiguousSwitchExample.java in order to see how returning from a case when using siwtch expressions would
cause ambiguity so the new keyword yield is to be used to resolve it. The keyword 'yield' is not resolved so you can
declare variables named yield anywhere except in a case block.

With the new syntax if you have all the possible enumerated values in your switch then there is no need for a default
clause.
