package cl.julio.algorithmsFourthEdition.chapter2.section5;

import cl.julio.algorithmsFourthEdition.chapter2.Common;

public class Exercise6 {

    public static void main(String[] args) {
        Comparable<Integer>[] a = new Comparable[] {
            31, 42, 2, 7, 4, 5, 6,
        };

        Comparable median = select(a, a.length / 2);
        System.out.println(median);

        for (int i = 0; i < a.length; ++i) {
            System.out.println(a[i]);
        }
    }

    public static Comparable select(Comparable[] a, int k) {
        int lo = 0;
        int hi = a.length - 1;

        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j == k)     break;
            else if (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
        }

        return a[k];
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo]; // Partitioning item (pivot).

        while (true) {
            while (Common.less(a[++i], v)) if (i == hi) break; // Scan from left to right. Stops when encounter an item greater than 'v'.
            while (Common.less(v, a[--j])) if (j == lo) break; // Scan from right to left. Stops when encounter an item less than 'v'.

            if (i >= j) break; // Pointers cross to the other side

            Common.exchange(a, i, j); // Leave item to the left (less than 'v') and to the right (greater than 'v').
        }

        Common.exchange(a, lo, j); // Place 'v' to the final position.

        return j;
    }

}
