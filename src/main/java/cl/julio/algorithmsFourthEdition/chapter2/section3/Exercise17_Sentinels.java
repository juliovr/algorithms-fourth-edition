package cl.julio.algorithmsFourthEdition.chapter2.section3;

import cl.julio.algorithmsFourthEdition.Common;

public class Exercise17_Sentinels {

    public static void main(String[] args) {
        Comparable[] data = Common.loadDataFromFile("test_data/20000_numbers");
//        Comparable[] data = new Comparable[] {
//                9, 8, 7, 3, 2, 1
//        };
        int n = data.length;

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
        // lookup the largest value and put in a.length - 1 to act as a sentinel
        int indexMax = 0;
        for (int i = 1; i < a.length; i++) {
            if (Common.less(a[indexMax], a[i])) {
                indexMax = i;
            }
        }
        
        Common.exchange(a, indexMax, a.length - 1);
        
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
            while (Common.less(a[++i], v)) ; // Scan from left to right. Stops when encounter an item greater than 'v'.
            while (Common.less(v, a[--j])) ; // Scan from right to left. Stops when encounter an item less than 'v'.

            if (i >= j) break; // Pointers cross to the other side
            
            Common.exchange(a, i, j); // Leave item to the left (less than 'v') and to the right (greater than 'v').
        }
        
        Common.exchange(a, lo, j); // Place 'v' to the final position.
        
        return j;
    }
    
}
