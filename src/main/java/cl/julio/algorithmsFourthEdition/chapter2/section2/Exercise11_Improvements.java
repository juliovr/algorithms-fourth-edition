package cl.julio.algorithmsFourthEdition.chapter2.section2;

import static cl.julio.algorithmsFourthEdition.chapter2.Common.less;

import java.util.HashMap;
import java.util.Map;

import cl.julio.algorithmsFourthEdition.chapter2.Common;
import cl.julio.algorithmsFourthEdition.chapter2.section1.Insertion;

public class Exercise11_Improvements {

    private static final int CUTOFF = 15;
    
    @SuppressWarnings({ "rawtypes" })
    public static void main(String[] args) throws Exception {
        int min = 200;
        int max = 200;

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
            System.out.println("Insertion sort");
            Insertion.sort(array);
            return;
        }

        System.out.println("Merge sort");
        int middle = low + (high - low) / 2;

        sort(array, aux, low, middle);
        sort(array, aux, middle + 1, high);

        // Improvement 2: Test whether the array is already in order.
        if (!Common.isSorted(array)) {
            merge(array, aux, low, middle, high);
        }
    }
    
    @SuppressWarnings({ "rawtypes" })
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int left = lo;
        int right = hi;
        for (int i = lo; i <= hi; i++) {
            if (less(aux[left], aux[right])) {
                a[i] = aux[left++];
            }
            else {
                a[i] = aux[right--];
            }
        }
    }
    
}
