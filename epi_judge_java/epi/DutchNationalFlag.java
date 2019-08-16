package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    Color pivot = A.get(pivotIndex);

    // Folowing lines of code grouping the numbers smaller than pivot.
    for (int i = 0; i < A.size(); i++) {
      for (int j = i + 1; j < A.size(); j++) {
        if (A.get(j).ordinal() < pivot.ordinal()) {
          Collections.swap(A, i, j);
          break;
        }
      }
    }

    // Folowing lines of ocde grouping the numbers larger than pivot.
    for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivot.ordinal(); i--) {
      for (int j = i -1; j >= 0 && A.get(j).ordinal() >= pivot.ordinal(); j--) {
        if (A.get(j).ordinal() > pivot.ordinal()) {
          Collections.swap(A, i, j);
          break;
        }
      }
    }
  }

  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
