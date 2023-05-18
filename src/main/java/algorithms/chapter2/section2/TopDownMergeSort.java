package algorithms.chapter2.section2;

import algorithms.Common;

public class TopDownMergeSort {

    public static void mergeSort(Comparable[] array) {
        Comparable[] aux = new Comparable[array.length];

        sort(array, aux, 0, array.length - 1);
    }

    private static void sort(Comparable[] array, Comparable[] aux, int low, int high) {
        if (high <= low) {
            return;
        }

        int middle = low + (high - low) / 2;

        sort(array, aux, low, middle);
        sort(array, aux, middle + 1, high);

        merge(array, aux, low, middle, high);
    }
    
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

//    @SuppressWarnings("unchecked")
//    private static void merge(Comparable[] array, Comparable[] aux, int low, int middle, int high) {
//        for (int i = low; i <= high; i++) {
//            aux[i] = array[i];
//        }
//
//        int indexLeft = low;
//        int indexRight = middle + 1;
//        int arrayIndex = low;
//
//        while (indexLeft <= middle && indexRight <= high) {
//            if (aux[indexLeft].compareTo(aux[indexRight]) <= 0) {
//                array[arrayIndex] = aux[indexLeft];
//                indexLeft++;
//            } else {
//                array[arrayIndex] = aux[indexRight];
//                indexRight++;
//            }
//            arrayIndex++;
//        }
//
//        while (indexLeft <= middle) {
//            array[arrayIndex] = aux[indexLeft];
//
//            indexLeft++;
//            arrayIndex++;
//        }
//    }

}
