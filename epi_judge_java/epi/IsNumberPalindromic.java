package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsNumberPalindromic {

  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    if (x <= 0) { // Negative numbers are not palindromic, 0 is palindromic
      return x == 0;
    }

    final int numDigits = (int)(Math.floor(Math.log10(x))) + 1; // numbers of digits in x-number
    int msdMask = (int)Math.pow(10, numDigits - 1); // Extract the MSD (most significant digit) from x

    for (int i = 0; i < (numDigits / 2); ++i) {
      if (x / msdMask != x % 10) { // If the LSD and MSD of x are not equal -> number is not palindromic
        return false;
      }

      x %= msdMask; // Remove the MSD of x
      x /= 10; // Remove the LSD of x
      msdMask /= 100;
    }

    return true;
    // -------------------------------------- //
    /* MY SOLUTION
    long result = 0;
    long xRemaining = Math.abs(x);

    while (xRemaining != 0) {
      result = result * 10 + xRemaining % 10;
      xRemaining /= 10;
    }

    return result == x;
    */
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
