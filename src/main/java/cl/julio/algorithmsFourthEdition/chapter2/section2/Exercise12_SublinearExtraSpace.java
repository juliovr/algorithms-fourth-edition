package cl.julio.algorithmsFourthEdition.chapter2.section2;

import static cl.julio.algorithmsFourthEdition.Common.less;

import cl.julio.algorithmsFourthEdition.Common;

public class Exercise12_SublinearExtraSpace {

    private static final int N = 1024;
    private static final int BLOCK_SIZE = N / 8;

    @SuppressWarnings({ "rawtypes" })
    public static void main(String[] args) {
        System.out.println("Generating data...");
        Comparable[] array = Common.generateRandomData(N);
        System.out.println("Done");
        
        System.out.println("Selection sort...");
        for (int i = 0; i < N; i += BLOCK_SIZE) {
            System.out.printf("Selection sort: low = %d, high = %d\n", i, (i + BLOCK_SIZE));
            selectionSort(array, i, i + BLOCK_SIZE);
        }
        System.out.println("Done");

        System.out.println("Top-Down merge sort...");
        long start = System.currentTimeMillis();
        mergeSort(array);
        long end = System.currentTimeMillis();
        
        System.out.printf("Time elapsed: %d ms\n", (end - start));

        System.out.println("is sorted? " + Common.isSorted(array));
        System.out.println("Done");
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
            if (less(aux[left], aux[right])) {
                a[i] = aux[left++];
            } else {
                a[i] = aux[right--];
            }
        }
    }
    
    public static void selectionSort(Comparable[] a, int low, int high) {
        for (int i = low; i < high; i++) {
            int min = i;
            for (int j = i + 1; j < high; j++) {
                if (Common.less(a[j], a[min])) {
                    min = j;
                }
            }
            
            Common.exchange(a, i, min);
        }
    }

}
