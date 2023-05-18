package algorithms.chapter2.section2;

import java.util.HashMap;
import java.util.Map;

import algorithms.Common;
import algorithms.chapter2.section1.Insertion;

public class Exercise11_Improvements {

    private static final int CUTOFF = 100;
    
    @SuppressWarnings({ "rawtypes" })
    public static void main(String[] args) throws Exception {
        int min = 300;
        int max = 300;

        System.out.println("Generating data...");
        Map<Integer, Comparable[]> arrays = new HashMap<>();
        for (int i = min; i <= max; i++) {
            arrays.put(i, Common.generateRandomData(i));
        }

        System.out.println("Top-Down merge sort");
        for (int i = min; i <= max; i++) {
            Comparable[] data = arrays.get(i);
            
            long start = System.currentTimeMillis();
            mergeSort(data);
            long end = System.currentTimeMillis();
            System.out.println("Time elapsed array " + i + " elements: " + (end - start) + " ms");
            if (!Common.isSorted(data)) {
                System.err.println("Data is not sorted!!");
            }
        }
    }
    
    @SuppressWarnings({ "rawtypes" })
    public static void mergeSort(Comparable[] array) {
        Comparable[] aux = new Comparable[array.length];
        
        // Improvement 3: Eliminate the copy to the auxiliary array. Just make the copy once.
        for (int i = 0; i < array.length; i++) {
            aux[i] = array[i];
        }

        sort(array, aux, 0, array.length - 1);
    }

    @SuppressWarnings({ "rawtypes" })
    private static void sort(Comparable[] array, Comparable[] aux, int low, int high) {
        // Improvement 1: Use insertion sort for small subarrays.
        if ((high - low) <= CUTOFF) {
            Insertion.sort(array);
            return;
        }
        
        int middle = low + (high - low) / 2;

        sort(aux, array, low, middle);
        sort(aux, array, middle + 1, high);

        // Improvement 2: Test whether the array is already in order.
        if (!Common.isSorted(array)) {
            merge(array, aux, low, middle, high);
        }
    }
    
    @SuppressWarnings({ "rawtypes" })
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                a[k] = aux[j++];
            else if (j > hi )                a[k] = aux[i++];
            else if (Common.less(aux[j], aux[i]))   a[k] = aux[j++];
            else                             a[k] = aux[i++];
        }
    }
    
}
