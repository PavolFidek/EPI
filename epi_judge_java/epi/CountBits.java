package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class CountBits {
  @EpiTest(testDataFile = "count_bits.tsv")

  public static short countBits(int x) {
    short numOfBits = 0;

    while (x != 0) {
      numOfBits += (x & 1); // Add to numOfBits the result of (x AND 1), check first bit of the word
                            // 00001001
                            // 00000001
                            // --------
                            // 00000001 => then the line of code increment numOfBits variable, else the variable will not change

      x >>>= 1; // Righ shift X (00001001 >>> 1 => 00000100)
    }

    return numOfBits;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CountBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
