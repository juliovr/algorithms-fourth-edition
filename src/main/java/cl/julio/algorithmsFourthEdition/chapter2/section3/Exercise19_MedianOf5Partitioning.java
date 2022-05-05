package cl.julio.algorithmsFourthEdition.chapter2.section3;

import cl.julio.algorithmsFourthEdition.chapter2.Common;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise19_MedianOf5Partitioning {

    public static void main(String[] args) {
        Comparable[] data = Common.loadDataFromFile("test_data/20000_numbers");
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
    
    /**
     * Method to try the computeMedianOf5 to all possible cases of combinations and correct answers.
     * 
        test(new Comparable[] { 3, 5, 11, 13, 15 }, 11);  // 3, 5, 11, 13, 15
        test(new Comparable[] { 3, 5, 11, 2, 50 }, 5);    // 2, 3, 5, 11, 50
        test(new Comparable[] { 3, 5, 11, 2, 1 }, 3);     // 1, 2, 3, 5, 11
        test(new Comparable[] { 3, 5, 11, 2, 1 }, 3);     // 1, 2, 3, 5, 11
        test(new Comparable[] { 5, 3, 11, 2, 1 }, 3);     // 1, 2, 3, 5, 11
        test(new Comparable[] { 3, 5, 11, 1, 2 }, 3);     // 1, 2, 3, 5, 11
        test(new Comparable[] { 11, 5, 3, 2, 1 }, 3);     // 1, 2, 3, 5, 11
        test(new Comparable[] { 3, 5, 11, 2, 1 }, 3);     // 1, 2, 3, 5, 11
        test(new Comparable[] { 3, 10, 15, 5, 20 }, 10);  // 3, 5, 10, 15, 20
        test(new Comparable[] { 3, 10, 15, 13, 20 }, 13); // 3, 10, 13, 15, 20
        test(new Comparable[] { 3, 10, 15, 17, 5 }, 10);  // 3, 5, 10, 15, 17
        test(new Comparable[] { 3, 10, 15, 17, 12 }, 12); // 3, 10, 12, 15, 17

     * @param a
     * @param expected
     */
    private static void test(Comparable[] a, Comparable expected) {
        Comparable v = computeMedianOf5(a);
        
        if (v.equals(expected)) {
            System.out.println("Median: " + v + "\t...... OK");
        } else {
            System.err.println("Median: " + v + "\t...... ERROR");
        }
    }
    
    private static Comparable computeMedianOf5(Comparable[] a) {
        Comparable v = a[2];
        
        if (Common.less(a[1], a[0])) {
            swap(a, 1, 0);
        }
        
        if (Common.less(a[2], a[0])) {
            swap(a, 2, 0);
        }
        
        if (Common.less(a[2], a[1])) {
            swap(a, 2, 1);
        }
        
        // sort 2 last elements
        if (Common.less(a[4], a[3])) {
            swap(a, 4, 3);
        }
        
        if (Common.less(a[4], a[0])) {
            v = a[0];
        } else if (Common.less(a[3], a[1])) {
            v = a[1];
        } else if (Common.less(a[3], a[2])) {
            v = a[3];
        }
        
        return v;
    }
    
    private static void swap(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
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

    // TODO: implement mediantOf5 method. I don't want to do it know because I solved the find median.
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        
        // lo - mid - hi
        // lo - hi - mid
        // mid - lo - hi
        // mid - hi - lo
        // hi - lo - mid
        // hi - mid - lo
        
        computeMedianOf5(a);
        
//        Common.exchange(a, mid, lo);
        
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
