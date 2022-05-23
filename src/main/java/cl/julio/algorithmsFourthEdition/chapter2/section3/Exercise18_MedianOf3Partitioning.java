package cl.julio.algorithmsFourthEdition.chapter2.section3;

import cl.julio.algorithmsFourthEdition.Common;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise18_MedianOf3Partitioning {

    public static void main(String[] args) {
        Comparable[] data = Common.loadDataFromFile("test_data/1498785_numbers");
//      Comparable[] data = new Comparable[] {
//              9, 8, 7, 3, 2, 1
//      };
        int n = data.length;
        
        StdRandom.shuffle(data);

        long start = System.currentTimeMillis();
        sort(data);
        long end = System.currentTimeMillis();

        long timeElapsed = end - start;
        System.out.println("Time elapsed array " + n + " elements: " + timeElapsed + " ms");

        if (Common.isSorted(data)) {
            System.out.println("OK");
        } else {
            System.err.println("Data is not sorted!!");
        }
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
        
        int mid = lo + ((hi - lo) / 2);
        
        // lo - mid - hi
        // lo - hi - mid
        // mid - lo - hi
        // mid - hi - lo
        // hi - lo - mid
        // hi - mid - lo
        
        if (Common.less(a[mid], a[lo])) {
            Common.exchange(a, mid, lo);
        }
        
        if (Common.less(a[hi], a[lo])) {
            Common.exchange(a, hi, lo);
        }
        
        if (Common.less(a[hi], a[mid])) {
            Common.exchange(a, hi, mid);
        }
        
        Common.exchange(a, mid, lo);
        
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
