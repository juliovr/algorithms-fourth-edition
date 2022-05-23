package cl.julio.algorithmsFourthEdition.chapter2.section3;

import cl.julio.algorithmsFourthEdition.Common;
import edu.princeton.cs.algs4.Stack;

public class Exercise20_NonrecursiveQuicksort {

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
    
    private static class ArrayPointer {
        int lo;
        int hi;
        
        ArrayPointer(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }
    }
    
    public static void sort(Comparable[] a) {
        Stack<ArrayPointer> stack = new Stack<>();
        stack.push(new ArrayPointer(0, a.length - 1));

        while (stack.size() > 0) {
            ArrayPointer pointers = stack.pop();
            
            int j = partition(a, pointers.lo, pointers.hi);
            ArrayPointer leftSubarrayPointers = new ArrayPointer(pointers.lo, j - 1);
            ArrayPointer rightSubarrayPointers = new ArrayPointer(j + 1, pointers.hi);
            
            // Ensure that the larger subarrays is push first.
            int leftSubarraySize = j - pointers.lo;
            int rightSubarraySize = pointers.hi - j;
            
            if (leftSubarraySize > rightSubarraySize) {
                if (leftSubarraySize > 1)  stack.push(leftSubarrayPointers);
                if (rightSubarraySize > 1) stack.push(rightSubarrayPointers);
            } else {
                if (rightSubarraySize > 1) stack.push(rightSubarrayPointers);
                if (leftSubarraySize > 1)  stack.push(leftSubarrayPointers);
            }
            
        }

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
