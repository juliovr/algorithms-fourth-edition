package algorithms.chapter2.section2;

import java.util.HashMap;
import java.util.Map;

import algorithms.Common;

public class Exercise10_FasterMerge {

    @SuppressWarnings({ "rawtypes" })
    public static void main(String[] args) {
        int min = 512;
        int max = 512;

        System.out.println("Generating data...");
        Map<Integer, Comparable[]> arrays = new HashMap<>();
        for (int i = min; i <= max; i++) {
            arrays.put(i, Common.generateRandomData(i));
        }

        System.out.println("Top-Down merge sort");
        for (int i = min; i <= max; i++) {
            Comparable[] data = arrays.get(i);

            mergeSort(data);
            Common.show(data);
            System.out.println(Common.isSorted(data));
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
        for (int i = lo; i <= mid; i++) {
            aux[i] = a[i];
        }
        
        // Reverse order for faster merge
        for (int i = mid + 1, l = hi; i <= hi && l > mid; i++, l--) {
            aux[i] = a[l];
        }

        int left = lo;
        int right = hi;
        for (int i = lo; i <= hi; i++) {
            if (Common.less(aux[left], aux[right])) {
                a[i] = aux[left++];
            }
            else {
                a[i] = aux[right--];
            }
        }
    }
    
}
