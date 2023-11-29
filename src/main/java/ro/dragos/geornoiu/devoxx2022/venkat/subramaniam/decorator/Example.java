package ro.dragos.geornoiu.devoxx2022.venkat.subramaniam.decorator;

import java.awt.*;
import java.util.function.Function;
import java.util.stream.Stream;

// This is a real life decorator pattern example. When you have a camera you can attach different
// filters (camera filters/lenses) to it in order to obtain a specific result
class Camera {
  private Function<Color, Color> cameraFilter;

  public Camera(Function<Color, Color>... cameraFilters) {
    // here is the magic, we apply all camera filters by combining them one after the other using
    // the reduce and the andThen function
    cameraFilter = Stream.of(cameraFilters).reduce(Function.identity(), Function::andThen);
  }

  public Color snap(Color input) {
    return cameraFilter.apply(input);
  }
}

public class Example {
  public static void print(Camera camera) {
    System.out.println(camera.snap(new Color(125, 125, 125)));
  }

  public static void main(String[] args) {
    print(new Camera());
    // we see we can pass a function now in order fo the fixed Color to be changed
    print(new Camera(color -> color.brighter()));
    print(new Camera(Color::brighter)); // don't forget method reference can be used
    print(new Camera(Color::darker));

    // and we can pass multiple functions
    print(new Camera(Color::brighter, Color::brighter));
  }
}
