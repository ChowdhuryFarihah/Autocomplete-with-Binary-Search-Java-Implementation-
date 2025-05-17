import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key,
                                         Comparator<Key> comparator) {

        exceptionThrower(a, key, comparator);

        int lo = 0;
        int hi = a.length - 1;

        while (lo < hi) {
            // locate the middle
            int mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(key, a[mid]);

            // if the middle key is less than the search key, shift the upper
            // bound down
            if (compare < 0) hi = mid - 1;

                // if the middle key is greater than the search key, shift the lower
                // bound up
            else if (compare > 0) lo = mid + 1;

                // if we found the key (the key is in the array), we shift the upper
                // bound down to continue looking to the left of the found key
            else {
                hi = mid;
            }
        }
        // if the search converges to the seach key, then the lower bound will
        // equal the search key and it will be the first index
        if (hi != -1 && comparator.compare(a[lo], key) == 0) {
            return lo;
        }
        return -1;

    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        exceptionThrower(a, key, comparator);
        int lo = 0;
        int hi = a.length - 1;

        while (lo < hi) {
            // locate the middle
            int mid = lo + (hi - lo) / 2;

            int compare = comparator.compare(key, a[mid]);

            // if the middle key is greater than the search key, shift the upper
            // bound down
            if (compare < 0) hi = mid - 1;

                // if the middle key is less than the search key, shift the lower
                // bound up
            else if (compare > 0) lo = mid + 1;

                // if we found the key (the key is in the array), we shift the lower
                // bound up to continue looking to the left of the found key
            else {

                lo = mid;

                // deals with corner case when hi and lo are next to eachother
                if (hi - lo == 1) {
                    if (comparator.compare(a[hi], key) == 0) return hi;
                    return lo;
                }

            }
        }
        // if the search converges to the seach key, then usually the lower
        // bound will equal the search key and it will be the last index
        if (hi != -1 && comparator.compare(a[lo], key) == 0) return lo;
        return -1;
    }

    // throws illegal argument exceptions if arguments are invalid
    private static <Key> void exceptionThrower(Key[] a, Key key,
                                               Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("Invalid Arguments");
        }

    }

    // Big Theta Linear algorithm to find the first index by iterating through
    // array from the front
    private static <Key> int bruteForceFirstIndex(Key[] a, Key key,
                                                  Comparator<Key> comparator) {
        for (int i = 0; i < a.length; i++) {
            if (comparator.compare(a[i], key) == 0) {
                return i;
            }
        }
        return -1;
    }

    // Big Theta Linear algorithm to find the first index by iterating through
    // array from the back
    private static <Key> int bruteForceLastIndex(Key[] a, Key key,
                                                 Comparator<Key> comparator) {
        for (int i = a.length - 1; i >= 0; i--) {
            if (comparator.compare(a[i], key) == 0) {
                return i;
            }
        }
        return -1;
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Integer[] testArray = new Integer[5 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                testArray[i * 5 + j] = i + 1;
            }
        }
        for (int i = 0; i < 5 * n; i++) {
            StdOut.print(testArray[i] + " ");
        }
        StdOut.println();
        int keyIndex = StdRandom.uniformInt(5 * n);
        StdOut.println("index: " + keyIndex);

        int key = testArray[keyIndex];
        int resultBinaryFirst = firstIndexOf(testArray, key, Integer::compare);
        int resultBruteFirst = bruteForceFirstIndex(testArray, key, Integer::compare);
        boolean firstIndexWorks = resultBinaryFirst == resultBruteFirst;
        StdOut.println("First Index works: " + firstIndexWorks);

        int resultBinaryLast = lastIndexOf(testArray, key, Integer::compare);
        int resultBruteLast = bruteForceLastIndex(testArray, key, Integer::compare);
        boolean lastIndexWorks = resultBinaryLast == resultBruteLast;
        StdOut.println("Last Index works: " + lastIndexWorks);

    }
}
