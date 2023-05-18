package algorithms.chapter2.section3;

import algorithms.Common;

public class Quick {
    
    public static void main(String[] args) throws Exception {
        System.out.println("Start");

        runExperiments(10);

        System.out.println("Done");
    }
    
    @SuppressWarnings({ "rawtypes" })
    private static void runExperiments(int numberExperiments) {
        for (int j = 0; j < 5; j++) {
            long min = Long.MAX_VALUE;
            long max = 0;
            long sum = 0;
            
            for (int i = 0; i < numberExperiments; i++) {
                Comparable[] data = Common.loadDataFromFile("test_data/20000_numbers");
                int n = data.length;

                long start = System.currentTimeMillis();
                Quick.sort(data);
                long end = System.currentTimeMillis();

                long timeElapsed = end - start;
                System.out.println("Time elapsed array " + n + " elements: " + timeElapsed + " ms");

                if (!Common.isSorted(data)) {
                    System.err.println("Data is not sorted!!");
                }
                
                if (timeElapsed < min)
                    min = timeElapsed;
                
                if (timeElapsed > max)
                    max = timeElapsed;
                
                sum += timeElapsed;
            }
            
            long avg = sum / numberExperiments;

            System.out.println("=========== Report ===========");
            System.out.printf("Merge sort\n");
            System.out.printf("Total time: %d\n", sum);
            System.out.printf("Min time:   %d\n", min);
            System.out.printf("Max time:   %d\n", max);
            System.out.printf("Avg time:   %d\n", avg);
            System.out.println("==============================");
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
