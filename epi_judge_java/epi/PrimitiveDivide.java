package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PrimitiveDivide {

  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    int result = 0;
    int power = 32;
    long yPover = y << power;

    while (x >= y) {
      while (yPover > x) {
        yPover >>>= 1;
        --power;
      }

      result += 1 << power;
      x -= yPover;
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
