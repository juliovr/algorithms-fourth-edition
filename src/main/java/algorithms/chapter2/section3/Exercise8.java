package algorithms.chapter2.section3;

import algorithms.Common;

public class Exercise8 {
    
    private static int compares = 0;
    
    public static void main (String[] args) {
        int n = 1_000_000;
        
        Comparable[] data = new Comparable[n];
        for (int i = 0; i < n; i++) {
            data[i] = 1;
        }
        
        sort(data);
        
        System.out.println("Number of compares = " + compares);
        
        double lnN = Math.log(n);
        //2N ln N
        int expected = (int) (2 * n * lnN);
        
//        int expected = (int) (2 * n * (Math.log10(n) / Math.log10(2))); // 2n ln(n)
        
        System.out.println("Expected = " + expected);
    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1); // Sort left
        sort(a, j + 1, hi); // Sort right
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo]; // Partitioning item (pivot).

        while (true) {
            while (Common.less(a[++i], v)) {
                ++compares;
                if (i == hi)
                    break; // Scan from left to right. Stops when encounter an item greater than 'v'.
            }
            ++compares; // previous last compare that not enter in the while block

            while (Common.less(v, a[--j])) {
                ++compares;
                if (j == lo)
                    break; // Scan from right to left. Stops when encounter an item less than 'v'.
            }
            ++compares; // previous last compare that not enter in the while block

            if (i >= j)
                break; // Pointers cross to the other side

            Common.exchange(a, i, j); // Leave item to the left (less than 'v') and to the right (greater than 'v').
        }

        Common.exchange(a, lo, j); // Place 'v' to the final position.

        return j;
    }

}
