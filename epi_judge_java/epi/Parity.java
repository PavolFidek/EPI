package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Parity {
  @EpiTest(testDataFile = "parity.tsv")

  public static short parity(long x) {
    short result = 0;

    while (x != 0) {
      result ^= 1; // Counting the parity (result will be only 0 - even, 1 - odd)
                   // e.g. 0001 ^ 0001 => 0000, 0000 ^ 0001 => 0001

      x &= (x - 1); // Clear the lowest bit in word
                   // e.g. 0110 (6) & 0101 (5) => 0100 (4)
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
