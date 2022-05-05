package cl.julio.algorithmsFourthEdition.chapter2.section3;

import cl.julio.algorithmsFourthEdition.chapter2.Common;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise28_RecursionDepth {

    private static final int CUTOFF = 0;
    private static int recursionDepth = -1;
    
    public static void main(String[] args) {
//        Comparable[] data = Common.loadDataFromFile("test_data/1498785_numbers");
        Comparable[] data = new Comparable[] {
                9, 8, 7, 3
        };
        int n = data.length;
        
//        StdRandom.shuffle(data);

        long start = System.currentTimeMillis();
        quicksort(data);
        long end = System.currentTimeMillis();

        long timeElapsed = end - start;
        System.out.println("Time elapsed array " + n + " elements: " + timeElapsed + " ms");

        if (Common.isSorted(data)) {
            System.out.println("OK");
        } else {
            System.err.println("Data is not sorted!!");
        }
        
        System.out.println("Recursion depth: " + recursionDepth);
    }
    
    public static void quicksort(Comparable[] a) {
        quicksort(a, 0, a.length - 1);
    }
    
    private static void quicksort(Comparable[] a, int lo, int hi) {
        ++recursionDepth;

        if ((hi - lo) < CUTOFF) {
            insertionsort(a, lo, hi);
            return;
        }
        
        // Due to cutoff to insertion sort, this condition will never happen.
//        if (hi <= lo)
//            return;
        
        int j = partition(a, lo, hi);
        quicksort(a, lo, j - 1); // Sort left
        quicksort(a, j + 1, hi); // Sort right
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
    
    public static void insertionsort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi + 1; i++) {
            for (int j = i; j > 0 && Common.less(a[j], a[j - 1]); j--) {
                Common.exchange(a, j, j - 1);
            }
        }
    }
    
}
