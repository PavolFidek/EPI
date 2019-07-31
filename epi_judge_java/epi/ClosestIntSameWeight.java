package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ClosestIntSameWeight {

  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    final int NUM_UNSIGNED_BITS = 63;

    for (int i = 0; i < NUM_UNSIGNED_BITS - 1; i++) {
      if (((x >>> i) & 1) != ((x >>> i + 1) & 1)) { // Check the pair of bits if are the same
        // x = SwapBits.swapBits(x, i, i + 1);
        x ^= (1L << i) | (1L << (i + 1)); // Swap bits

        return x;
      }
    }

    throw new IllegalArgumentException("All bits are 0 or 1");
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
