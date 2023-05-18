package algorithms.chapter2.section2;

import java.util.HashMap;
import java.util.Map;

import algorithms.Common;

public class Exercise17_LinkedListSort {

    @SuppressWarnings({ "rawtypes" })
    public static void main(String[] args) throws Exception {
        int min = 200;
        int max = 200;

        System.out.println("Generating data...");
        Map<Integer, Comparable[]> arrays = new HashMap<>();
        for (int i = min; i <= max; i++) {
            arrays.put(i, Common.generateRandomData(i));
        }

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

        sort(array, aux, 0, array.length - 1);
    }

    @SuppressWarnings({ "rawtypes" })
    private static void sort(Comparable[] array, Comparable[] aux, int low, int high) {
        if (high <= low) {
            return;
        }

        int middle = low + (high - low) / 2;

        sort(array, aux, low, middle);
        sort(array, aux, middle + 1, high);

        merge(array, aux, low, middle, high);
    }
    
    @SuppressWarnings({ "rawtypes" })
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                a[k] = aux[j++];
            else if (j > hi )                a[k] = aux[i++];
            else if (Common.less(aux[j], aux[i]))   a[k] = aux[j++];
            else                             a[k] = aux[i++];
        }
    }

}
