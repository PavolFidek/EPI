package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PrimitiveMultiply {

  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    long sum = 0;

    while (x != 0) {
      if ((x & 1) != 0) {
        sum = add(sum, y);
      }

      x >>>=1;
      y <<= 1;
    }

    return sum;
  }

  // Create method for adding
  public static long add(long a, long b) {
    long sum = 0, carryin = 0, k = 1, tempA = a, tempB = b;

    while (tempA != 0 || tempB != 0) {
      long ak = a & k, bk = b & k;  // Chceck bit on k-th position
      long carryout = (ak & bk) | (ak & carryin) | (bk & carryin);  // Chceck if the bit is carrying to previous position

      sum |= ak ^ bk ^ carryin;
      carryin = carryout << 1;

      k <<= 1;
      tempA >>>= 1;
      tempB >>>= 1;
    }

    return sum | carryin;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
