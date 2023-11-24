package ro.dragos.geornoiu.jep.cafe.text.blocks;

public class TextBlockExample {

  public static void main(String[] args) {
//    String textBlock  = """ """; //you have to have a new line present when using a textblock, this will have a compilation error.
    String textBlock  = """ 
            text block  """;

    //the white space which are added for readability or for the ide are removed automatically by the compiler.
    String html = """
    <html>
      <body>
        <p>Hello</p>
      </body>
    </html>
    """;
    System.out.println(html);

// if you use the "\" at the end of a line, it will not create a new line for the text that follows
//    be careful to not leave a space after the usage of "\" because it will be a compilation error.
    String html2 = """
    <html>  \
      <body>  \
        <p>Hello</p>  \
      </body> \
    </html> \
    """;
    System.out.println(html2);

//    the \s also is accepted by the compiler. It can be used to keep white spaces at the end of the line
    String html3 = """
    <html>  \s 
      <body>  \s 
        <p>Hello</p>  \s
      </body> \s
    </html> \s
    """;
    System.out.println(html3);
  }
}
