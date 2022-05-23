package cl.julio.algorithmsFourthEdition.chapter2.section2;

import java.util.HashMap;
import java.util.Map;

import cl.julio.algorithmsFourthEdition.Common;

public class Exercise7 {

    private static int compares = 0;

    @SuppressWarnings({ "rawtypes" })
    public static void main(String[] args) {
        int min = 1;
        int max = 512;

        System.out.println("Generating data...");
        Map<Integer, Comparable[]> arrays = new HashMap<>();
        for (int i = min; i <= max; i++) {
            arrays.put(i, Common.generateRandomData(i));
        }

        System.out.println("Top-Down merge sort");
        int previousCompares = -1;
        for (int i = min; i <= max; i++) {
            compares = 0;

            Comparable[] data = arrays.get(i);
            mergeSort(data);
            
            if (compares > previousCompares) {
                previousCompares = compares;
            } else {
                System.out.println("Doesn't match. n = " + i);
            }
        }
    }

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

    @SuppressWarnings("unchecked")
    private static void merge(Comparable[] array, Comparable[] aux, int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            aux[i] = array[i];
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

            compares++;
        }

        while (indexLeft <= middle) {
            array[arrayIndex] = aux[indexLeft];

            indexLeft++;
            arrayIndex++;
        }
    }

}
