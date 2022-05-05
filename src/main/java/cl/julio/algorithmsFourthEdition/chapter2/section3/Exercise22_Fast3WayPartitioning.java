package cl.julio.algorithmsFourthEdition.chapter2.section3;

import cl.julio.algorithmsFourthEdition.chapter2.Common;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise22_Fast3WayPartitioning {

    public static void main(String[] args) {
        Comparable[] data = Common.loadDataFromFile("test_data/20000_numbers");
//        Comparable[] data = new Comparable[] {
//                2, 2, 1, 1, 9, 9, 8, 7, 3, 2, 1, 1
//        };
        int n = data.length;
        
        StdRandom.shuffle(data);
        
//        Common.showOneLine(data);

        long start = System.currentTimeMillis();
        sort(data);
        long end = System.currentTimeMillis();
        
        long timeElapsed = end - start;
        System.out.println("Time elapsed array " + n + " elements: " + timeElapsed + " ms");

//        Common.showOneLine(data);
        
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
        int p = lo;
        int q = hi + 1;
        
        Comparable v = a[lo]; // Partitioning item (pivot).
        
        while (true) {
            if (i > lo && Common.equals(v, a[i])) {
                Common.exchange(a, i, ++p);
            }
            
            if (j <= hi && Common.equals(v, a[j])) {
                Common.exchange(a, j, --q);
            }
            
            while (Common.less(a[++i], v)) 
                if (i == hi) 
                    break; // Scan from left to right. Stops when encounter an item greater than 'v'.

            while (Common.less(v, a[--j])) 
                if (j == lo) 
                    break; // Scan from right to left. Stops when encounter an item less than 'v'.

            if (i >= j) break; // Pointers cross to the other side
            
            Common.exchange(a, i, j); // Leave item to the left (less than 'v') and to the right (greater than 'v').
        }
        
        Common.exchange(a, lo, j); // Place 'v' to the final position.
        
        // Now:
        // a[lo..p] == v
        // a[
        // a[p+1..q] > v
        // a[q+1..hi] == v
        
        // place equals keys in the final position
//        for (int k = lo; lo <= j; k++) {
//            Common.exchange(a, k, j);
//        }
        
        return j;
    }
    
}
