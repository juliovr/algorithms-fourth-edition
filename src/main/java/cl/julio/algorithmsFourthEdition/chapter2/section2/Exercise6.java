package cl.julio.algorithmsFourthEdition.chapter2.section2;

import java.util.HashMap;
import java.util.Map;

import cl.julio.algorithmsFourthEdition.chapter2.Common;

/**
 * Write a program to compute the exact value of the number of array accesses
 * used by top-down mergesort and by bottom-up mergesort. Use your program to
 * plot the values for n from 1 to 512, and to compare the exact values with the
 * upper bound 6n lg n.
 * 
 * @author julio
 *
 */
public class Exercise6 {

    private static double log2 = Math.log10(2);
    private static int arrayAccesses = 0;

    @SuppressWarnings({ "rawtypes" })
    public static void main(String[] args) {
        int min = 1;
        int max = 512;

        System.out.println("Generating data...");
        Map<Integer, Comparable[]> arrays1 = new HashMap<>();
        Map<Integer, Comparable[]> arrays2 = new HashMap<>();
        for (int i = min; i <= max; i++) {
            arrays1.put(i, Common.generateRandomData(i));
            arrays2.put(i, Common.generateRandomData(i));
        }

        System.out.println("Top-Down merge sort");
        System.out.printf("n\t\taccesses\tupper bound\n");
        for (int i = min; i <= max; i++) {
            arrayAccesses = 0;

            Comparable[] data = arrays1.get(i);

            topDownMergeSort(data);
            
            System.out.printf("%d\t\t%d\t\t%.2f\n", i, arrayAccesses, upperBound(i));
        }
        
        System.out.println("Bottom-Up merge sort");
        System.out.printf("n\t\taccesses\tupper bound\n");
        for (int i = min; i <= max; i++) {
            arrayAccesses = 0;

            Comparable[] data = arrays2.get(i);

            bottomUpMergeSort(data);
            
            System.out.printf("%d\t\t%d\t\t%.2f\n", i, arrayAccesses, upperBound(i));
        }
    }

    @SuppressWarnings({ "rawtypes" })
    public static void topDownMergeSort(Comparable[] array) {
        Comparable[] aux = new Comparable[array.length];

        topDownMergeSort(array, aux, 0, array.length - 1);
    }

    @SuppressWarnings({ "rawtypes" })
    private static void topDownMergeSort(Comparable[] array, Comparable[] aux, int low, int high) {
        if (high <= low) {
            return;
        }

        int middle = low + (high - low) / 2;

        topDownMergeSort(array, aux, low, middle);
        topDownMergeSort(array, aux, middle + 1, high);

        merge(array, aux, low, middle, high);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void merge(Comparable[] array, Comparable[] aux, int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            aux[i] = array[i];

            arrayAccesses += 2;
        }

        int indexLeft = low;
        int indexRight = middle + 1;
        int arrayIndex = low;

        while (indexLeft <= middle && indexRight <= high) {
            if (aux[indexLeft].compareTo(aux[indexRight]) <= 0) {
                array[arrayIndex] = aux[indexLeft];
                indexLeft++;
            } else {
                array[arrayIndex] = aux[indexRight];
                indexRight++;
            }
            arrayIndex++;

            arrayAccesses += 4;
        }

        while (indexLeft <= middle) {
            array[arrayIndex] = aux[indexLeft];

            indexLeft++;
            arrayIndex++;

            arrayAccesses += 2;
        }
    }
    
    @SuppressWarnings({ "rawtypes" })
    public static void bottomUpMergeSort(Comparable[] array) {
        Comparable[] aux = new Comparable[array.length];

        for (int size = 1; size < array.length; size = size + size) {
            for (int low = 0; low + size < array.length; low += size + size) {
                int high = Math.min(low + size + size - 1, array.length - 1);
                merge(array, aux, low, low + size - 1, high);
            }
        }
    }

    private static double upperBound(int n) {
        return 6 * n * (Math.log10(n) / log2);
    }

}
