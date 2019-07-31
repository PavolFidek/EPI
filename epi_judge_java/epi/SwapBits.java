package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SwapBits {

  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    if (((x >>> i) & 1) != ((x >>> j) & 1)) {    // Check if the bits are the same, if bits are the same method just return the original 'x'
      long bitMask = (1L << i) | (1L << j);      // Create bit mask for swap bits, e.g. 00000010 | 01000000 => 01000010 (bit mask)
      x ^= bitMask;                              // Applay bit mask to x, XOR operator (^) will swap the bits on given indexes
    }

    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
