package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseDigits {

  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {
    long result = 0;
    long remaining = Math.abs(x);

    while (remaining != 0) {
      result = result * 10 + remaining % 10; // result * 10 "shift" the result walue to left e.g. from 2 to 20,
                                             // remaining % 10 add the next number from remaining to result e.g. from 20 to 23 (the "next" number was 3)

      remaining /= 10; // shift the remaining numbers to right e.g. from 1132 to 113
    }

    return x < 0 ? -result : result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
