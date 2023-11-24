# Text blocks

URL: https://www.youtube.com/watch?v=NDaA9MrTLBM&list=PLX8CzqL3ArzV4BpOzLanxd4bZr46x5e87&index=17

JEP 378

Text blocks is a way of writing strings of characters spanning several lines of your java program and to avoid the need
for most escaped sequences.

It was added in

Text blocks are instances of String and the "+" operator on a String would still work for Text Blocks.

Text blocks feature is compatible with Strings before it was added as a feature, so if you would copy an existing string
with its escaped sequences and paste it in a Text Block you would get the same result.

Text blocks do not support String interpolation. String interpolation is about adding references to external variables
within a String of characters and seeing this references replaced with the value of these variables. String
interpolation might be considered in a future JEP. For now the formated() method in the String class, which was added in
JDK 15, can be used

        String format = " | %6.3f | %6.3f |";
        String formatted = format.formatted(Math.PI, Map.E);

The result of formatted will be "| 3.142 | 2.718 |"

How to use the Text Block can be seen in he TextBlockExample class.

