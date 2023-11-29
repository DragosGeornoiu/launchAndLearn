# Factory Methods for Collections

URL: https://www.youtube.com/watch?v=rRtirh1nC5A&list=PLX8CzqL3ArzV4BpOzLanxd4bZr46x5e87&index=16

JEP 269

The main purpose of this JEP was to provide a better way to create non-empty lists/collections in the JDK.

Before JEP 269, this is how a collection would have been created:

    List<String> strings = new ArrayList();
    strings.add("one");
    strings.add("two");
    strings.add("three");

And one more line of code was needed to obtain an unmodifiable list:

    strings = Collections.unmodifiableList(strings);

One downside of the Collections.unmodifiableList(...) is that even if the list is already unmodifible, a new
unmodifiable list will be created at each call:

    strings = Collections.unmodifiableList(strings); //creates new list
    strings = Collections.unmodifiableList(strings); //creates new list
    strings = Collections.unmodifiableList(strings); //creates new list

The same applies for sets and map.

Now you can write the following code:

    List<String> strings = List.of("one", "two", "three"); //this is imutable list. also this list cannot contain null values.
    List<String> mutableStrings = new ArrayList(strings); //this creates a mutable list from the imutable list

The same applies to Set.of and Map.of.

For adding the first 10 elements, for List, Set and Map, the methods are overloaded with the elemnts as parameters
increased for each variant. After 10 elements, the List and Set use varargs and Map, since it needs both keys and vales,
thus not able to use varargs, can be used in the following way when over 10 elements:

    Map<Integer, String> map = Map.ofEntries(Map.entry(1,"1"), Map.entry(2,"2"), Map.entry(3,"3"));

The old Arrays.asList also exists in the JDK

    List<String> strings = Arrays.asList("one", "two", "three"); //this is not quite an unmodifiable list
    strings.add("four"); // throws UnsupportedOperationException.
    strings.replaceAll(String::toUppercase); // this unfortunatelly works, as the eleemtns can be chagned when using the 
                                             // Arrays.asList, it si more of a wrapper which does not allow adding and removing elements
    //also null values are accepted in this list

In JDK 10 it some extra factory methods were added:

    List<String> strings = ...;
    List<String> copy = List.copyOf(strings); 
    //the copy list is unmutable
    //the copy list does not accept null values, so we might need to fitler out null values before
    strings.removeIf(Objects::isNull);
    List<String> copy = List.copyOf(strings);
    //the copy list is serializable 
    //if the parameter of the copyOf was created using one of the methods that creates an imutable list, there is no 
    // need to be copied, it will be directly returned, an optimization which we did not have with Collections.unmodifiableList

In JDK 16 the Stream.toList was added

    List<String> strings = ...;
    List<String> list = strings.stream().map(String::toUpperCase).toList();
    //the new Stram.toList is not fully a copy of the toList shown previously
    //Stream.toList prodces an inmutable list, Collectors.toList produces a good old ArrayList
    //Stream.toList does allow null values. This is different to the others so far. It was done in order to replace 
    //    the Collectors.toList with the Stream.toList and thus be compatible because Collectors.toList allows null values
    

Wrap up:

                        |   Immutable           | Null values

    ArrayList           |   NO                  | YES

    Arrays.asList       |   Cannot add/remove   | YES
                            Can modify          

    List.of             |   YES                 | NO
    Set.of         

    List.copyOf         |   YES                 | NO
                        
    stream.toList       |   YES                 | NO

